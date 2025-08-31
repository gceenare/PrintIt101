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
import za.ac.cput.domain.Employee;
import za.ac.cput.factory.EmployeeFactory;
import za.ac.cput.service.EmployeeService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@AutoConfigureWebMvc
@Transactional
@Rollback
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class EmployeeControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private ObjectMapper objectMapper;

    private MockMvc mockMvc;
    private Employee employee;

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
                .setEmail("jane@example.com")
                .build();
    }

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        employee = EmployeeFactory.createEmployee(
                sampleAddress(), sampleContact(),
                "Jane", "Smith",
                "Manager", 15.0, "janeS", "pass456", "Employee"
        );
    }

    @Test
    @Order(1)
    void create() throws Exception {
        String employeeJson = objectMapper.writeValueAsString(employee);

        mockMvc.perform(post("/api/employees")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(employeeJson))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.firstName").value("Jane"))
                .andExpect(jsonPath("$.lastName").value("Smith"))
                .andExpect(jsonPath("$.userName").value("janeS"))
                .andExpect(jsonPath("$.position").value("Manager"))
                .andExpect(jsonPath("$.staffDiscount").value(15.0))
                .andExpect(jsonPath("$.userId").exists());

        System.out.println("Create Employee Test Passed");
    }

    @Test
    @Order(2)
    void read() throws Exception {
        Employee saved = employeeService.create(employee);

        mockMvc.perform(get("/api/employees/{id}", saved.getUserId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value("Jane"))
                .andExpect(jsonPath("$.lastName").value("Smith"))
                .andExpect(jsonPath("$.userName").value("janeS"))
                .andExpect(jsonPath("$.position").value("Manager"))
                .andExpect(jsonPath("$.userId").value(saved.getUserId()));

        System.out.println("Read Employee Test Passed");
    }

    @Test
    @Order(3)
    void update() throws Exception {
        Employee saved = employeeService.create(employee);

        Employee updatedEmployee = new Employee.Builder()
                .copy(saved)
                .setFirstName("John")
                .setLastName("Doe")
                .setPosition("Senior Manager")
                .setStaffDiscount(20.0)
                .build();

        String updatedEmployeeJson = objectMapper.writeValueAsString(updatedEmployee);

        mockMvc.perform(put("/api/employees")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(updatedEmployeeJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value("John"))
                .andExpect(jsonPath("$.lastName").value("Doe"))
                .andExpect(jsonPath("$.position").value("Senior Manager"))
                .andExpect(jsonPath("$.staffDiscount").value(20.0))
                .andExpect(jsonPath("$.userId").value(saved.getUserId()));

        System.out.println("Update Employee Test Passed");
    }

    @Test
    @Order(4)
    void delete() throws Exception {
        Employee saved = employeeService.create(employee);

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/employees/{id}", saved.getUserId()))
                .andExpect(status().isNoContent());

        // Verify deletion
        mockMvc.perform(get("/api/employees/{id}", saved.getUserId()))
                .andExpect(status().isNotFound());

        System.out.println("Delete Employee Test Passed");
    }

    @Test
    @Order(5)
    void getAll() throws Exception {
        employeeService.create(employee);

        mockMvc.perform(get("/api/employees"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(org.hamcrest.Matchers.greaterThan(0)));

        System.out.println("Get All Employees Test Passed");
    }

    @Test
    @Order(6)
    void findByFirstName() throws Exception {
        employeeService.create(employee);

        mockMvc.perform(get("/api/employees/firstname/{firstName}", "Jane"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].firstName").value("Jane"));

        System.out.println("Find By First Name Test Passed");
    }

    @Test
    @Order(7)
    void findByLastName() throws Exception {
        employeeService.create(employee);

        mockMvc.perform(get("/api/employees/lastname/{lastName}", "Smith"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].lastName").value("Smith"));

        System.out.println("Find By Last Name Test Passed");
    }

    @Test
    @Order(8)
    void findByPosition() throws Exception {
        employeeService.create(employee);

        mockMvc.perform(get("/api/employees/position/{position}", "Manager"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].position").value("Manager"));

        System.out.println("Find By Position Test Passed");
    }

    @Test
    @Order(9)
    void findByUserName() throws Exception {
        Employee uniqueEmployee = EmployeeFactory.createEmployee(
                sampleAddress(), sampleContact(),
                "Jane", "Smith",
                "Manager", 15.0, "janeS" + System.nanoTime(), "pass456", "Employee"
        );
        Employee saved = employeeService.create(uniqueEmployee);
        assertNotNull(saved);
        assertNotNull(saved.getUserName());

        mockMvc.perform(get("/api/employees/username/{userName}", saved.getUserName()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userName").value(saved.getUserName()))
                .andExpect(jsonPath("$.firstName").value("Jane"));

        System.out.println("Find By Username Test Passed");
    }

    @Test
    @Order(10)
    void existsByUserName() throws Exception {
        Employee uniqueEmployee = EmployeeFactory.createEmployee(
                sampleAddress(), sampleContact(),
                "Jane", "Smith",
                "Manager", 15.0, "janeS" + System.nanoTime(), "pass456", "Employee"
        );
        Employee saved = employeeService.create(uniqueEmployee);
        assertNotNull(saved);
        assertNotNull(saved.getUserName());

        mockMvc.perform(get("/api/employees/exists/{userName}", saved.getUserName()))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));

        // Test non-existent username
        mockMvc.perform(get("/api/employees/exists/{userName}", "nonexistent"))
                .andExpect(status().isOk())
                .andExpect(content().string("false"));

        System.out.println("Exists By Username Test Passed");
    }

    // ------------------ Invalid Input Tests ------------------
    @Test
    @Order(11)
    void createInvalidEmployee() throws Exception {
        Employee invalidEmployee = EmployeeFactory.createEmployee(
                sampleAddress(), sampleContact(),
                "", "Smith", // Empty first name
                "Manager", 15.0, "janeS" + System.nanoTime(), "pass456", "Employee"
        );

        String invalidEmployeeJson = objectMapper.writeValueAsString(invalidEmployee);

        mockMvc.perform(post("/api/employees")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(invalidEmployeeJson))
                .andExpect(status().isBadRequest());

        System.out.println("Create Invalid Employee Test Passed");
    }

    @Test
    @Order(12)
    void readNonExistentEmployee() throws Exception {
        mockMvc.perform(get("/api/employees/{id}", 99999))
                .andExpect(status().isNotFound());

        System.out.println("Read Non-existent Employee Test Passed");
    }

    @Test
    @Order(13)
    void createEmployeeWithInvalidPosition() throws Exception {
        Employee invalidEmployee = EmployeeFactory.createEmployee(
                sampleAddress(), sampleContact(),
                "Jane", "Smith",
                "", 15.0, "janeS" + System.nanoTime(), "pass456", "Employee" // Empty position
        );

        String invalidEmployeeJson = objectMapper.writeValueAsString(invalidEmployee);

        mockMvc.perform(post("/api/employees")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(invalidEmployeeJson))
                .andExpect(status().isBadRequest());

        System.out.println("Create Employee with Invalid Position Test Passed");
    }

    @Test
    @Order(14)
    void createEmployeeWithNegativeDiscount() throws Exception {
        Employee invalidEmployee = EmployeeFactory.createEmployee(
                sampleAddress(), sampleContact(),
                "Jane", "Smith",
                "Manager", -5.0, "janeS" + System.nanoTime(), "pass456", "Employee" // Negative discount
        );

        String invalidEmployeeJson = objectMapper.writeValueAsString(invalidEmployee);

        mockMvc.perform(post("/api/employees")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(invalidEmployeeJson))
                .andExpect(status().isBadRequest());

        System.out.println("Create Employee with Negative Discount Test Passed");
    }
}