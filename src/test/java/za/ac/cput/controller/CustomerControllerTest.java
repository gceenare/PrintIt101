package za.ac.cput.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureWebMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;
import za.ac.cput.domain.Address;
import za.ac.cput.domain.Contact;
import za.ac.cput.domain.Customer;
import za.ac.cput.factory.CustomerFactory;
import za.ac.cput.service.CustomerService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureWebMvc
@Transactional
@Rollback
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CustomerControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private ObjectMapper objectMapper;

    private MockMvc mockMvc;
    private Customer customer;

    private Address sampleAddress() {
        return new Address.Builder()
                .setBuildingName("Sunset Apartments")
                .setPropertyNumber(123)
                .setStreet("Main St")
                .setMunicipality("Cape Town")
                .setProvince("Western Cape")
                .setPostalCode("8000")
                .setCountry("South Africa")
                .build();
    }

    private Contact sampleContact() {
        return new Contact.Builder()
                .setPhoneNumber("0211234567")
                .setEmail("john@example.com")
                .build();
    }

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        customer = CustomerFactory.createCustomer(
                sampleAddress(), sampleContact(),
                "John", "Doe",
                10.0, "johnD", "pass123", "Customer"
        );
    }

    @Test
    @Order(1)
    void create() throws Exception {
        String customerJson = objectMapper.writeValueAsString(customer);

        mockMvc.perform(post("/api/customers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(customerJson))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.firstName").value("John"))
                .andExpect(jsonPath("$.lastName").value("Doe"))
                .andExpect(jsonPath("$.userName").value("johnD"))
                .andExpect(jsonPath("$.customerDiscount").value(10.0))
                .andExpect(jsonPath("$.userId").exists());

        System.out.println("Create Customer Test Passed");
    }

    @Test
    @Order(2)
    void read() throws Exception {
        Customer saved = customerService.create(customer);

        mockMvc.perform(get("/api/customers/{id}", saved.getUserId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value("John"))
                .andExpect(jsonPath("$.lastName").value("Doe"))
                .andExpect(jsonPath("$.userName").value("johnD"))
                .andExpect(jsonPath("$.userId").value(saved.getUserId()));

        System.out.println("Read Customer Test Passed");
    }

    @Test
    @Order(3)
    void update() throws Exception {
        Customer saved = customerService.create(customer);

        Customer updatedCustomer = new Customer.Builder()
                .copy(saved)
                .setFirstName("Jane")
                .setLastName("Smith")
                .setCustomerDiscount(15.0)
                .build();

        String updatedCustomerJson = objectMapper.writeValueAsString(updatedCustomer);

        mockMvc.perform(put("/api/customers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(updatedCustomerJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value("Jane"))
                .andExpect(jsonPath("$.lastName").value("Smith"))
                .andExpect(jsonPath("$.customerDiscount").value(15.0))
                .andExpect(jsonPath("$.userId").value(saved.getUserId()));

        System.out.println("Update Customer Test Passed");
    }

    @Test
    @Order(4)
    void delete() throws Exception {
        Customer saved = customerService.create(customer);

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/customers/{id}", saved.getUserId()))
                .andExpect(status().isNoContent());

        // Verify deletion
        mockMvc.perform(get("/api/customers/{id}", saved.getUserId()))
                .andExpect(status().isNotFound());

        System.out.println("Delete Customer Test Passed");
    }

    @Test
    @Order(5)
    void getAll() throws Exception {
        customerService.create(customer);

        mockMvc.perform(get("/api/customers"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(org.hamcrest.Matchers.greaterThan(0)));

        System.out.println("Get All Customers Test Passed");
    }

    @Test
    @Order(6)
    void findByFirstName() throws Exception {
        customerService.create(customer);

        mockMvc.perform(get("/api/customers/firstname/{firstName}", "John"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].firstName").value("John"));

        System.out.println("Find By First Name Test Passed");
    }

    @Test
    @Order(7)
    void findByLastName() throws Exception {
        customerService.create(customer);

        mockMvc.perform(get("/api/customers/lastname/{lastName}", "Doe"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].lastName").value("Doe"));

        System.out.println("Find By Last Name Test Passed");
    }

    @Test
    @Order(8)
    void findByUserName() throws Exception {
        // Create a customer with unique username for this test
        Customer uniqueCustomer = CustomerFactory.createCustomer(
                sampleAddress(), sampleContact(),
                "John", "Doe",
                10.0, "uniqueUser" + System.currentTimeMillis(), "pass123", "Customer"
        );
        Customer saved = customerService.create(uniqueCustomer);

        mockMvc.perform(get("/api/customers/username/{userName}", saved.getUserName()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userName").value(saved.getUserName()))
                .andExpect(jsonPath("$.firstName").value("John"));

        System.out.println("Find By Username Test Passed");
    }

    @Test
    @Order(9)
    void existsByUserName() throws Exception {
        // Create a customer with unique username for this test
        Customer uniqueCustomer = CustomerFactory.createCustomer(
                sampleAddress(), sampleContact(),
                "John", "Doe",
                10.0, "existsUser" + System.currentTimeMillis(), "pass123", "Customer"
        );
        Customer saved = customerService.create(uniqueCustomer);

        mockMvc.perform(get("/api/customers/exists/{userName}", saved.getUserName()))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));

        // Test non-existent username
        mockMvc.perform(get("/api/customers/exists/{userName}", "nonexistent"))
                .andExpect(status().isOk())
                .andExpect(content().string("false"));

        System.out.println("Exists By Username Test Passed");
    }

    // ------------------ Invalid Input Tests ------------------
    @Test
    @Order(10)
    void createInvalidCustomer() throws Exception {
        Customer invalidCustomer = CustomerFactory.createCustomer(
                sampleAddress(), sampleContact(),
                "", "Doe", // Empty first name
                10.0, "johnD", "pass123", "Customer"
        );

        String invalidCustomerJson = objectMapper.writeValueAsString(invalidCustomer);

        mockMvc.perform(post("/api/customers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(invalidCustomerJson))
                .andExpect(status().isBadRequest());

        System.out.println("Create Invalid Customer Test Passed");
    }

    @Test
    @Order(11)
    void readNonExistentCustomer() throws Exception {
        mockMvc.perform(get("/api/customers/{id}", 99999))
                .andExpect(status().isNotFound());

        System.out.println("Read Non-existent Customer Test Passed");
    }
}