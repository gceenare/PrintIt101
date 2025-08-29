package za.ac.cput.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import za.ac.cput.domain.Address;
import za.ac.cput.domain.Contact;
import za.ac.cput.domain.Customer;
import za.ac.cput.factory.CustomerFactory;
import za.ac.cput.repository.CustomerRepository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@Rollback
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CustomerServiceTest {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private CustomerRepository customerRepository;

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
        customer = CustomerFactory.createCustomer(
                sampleAddress(), sampleContact(),
                "John", "Doe",
                10.0, "johnD", "pass123", "Customer"
        );
    }

    @Test
    @Order(1)
    void create() {
        Customer saved = customerService.create(customer);
        assertNotNull(saved);
        assertEquals("John", saved.getFirstName());
        assertEquals("Doe", saved.getLastName());
        assertEquals("johnD", saved.getUserName());
        assertEquals(10.0, saved.getCustomerDiscount());
        assertTrue(saved.getUserId() > 0);
        System.out.println("Created Customer: " + saved);
    }

    @Test
    @Order(2)
    void read() {
        Customer saved = customerRepository.save(customer);
        Customer found = customerService.read(saved.getUserId());
        assertNotNull(found);
        assertEquals("John", found.getFirstName());
        assertEquals("Doe", found.getLastName());
        assertEquals(saved.getUserId(), found.getUserId());
        System.out.println("Read Customer: " + found);
    }

    @Test
    @Order(3)
    void update() {
        Customer saved = customerRepository.save(customer);
        Customer updatedCustomer = new Customer.Builder()
                .copy(saved)
                .setFirstName("Jane")
                .setLastName("Smith")
                .setCustomerDiscount(15.0)
                .build();
        Customer updated = customerService.update(updatedCustomer);
        assertNotNull(updated);
        assertEquals("Jane", updated.getFirstName());
        assertEquals("Smith", updated.getLastName());
        assertEquals(15.0, updated.getCustomerDiscount());
        assertEquals(saved.getUserId(), updated.getUserId());
        System.out.println("Updated Customer: " + updated);
    }

    @Test
    @Order(4)
    void delete() {
        Customer saved = customerRepository.save(customer);
        boolean deleted = customerService.delete(saved.getUserId());
        assertTrue(deleted);
        Customer found = customerService.read(saved.getUserId());
        assertNull(found);
        System.out.println("Customer deleted successfully");
    }

    @Test
    @Order(5)
    void getAll() {
        customerService.create(customer);
        List<Customer> customers = customerService.getAll();
        assertNotNull(customers);
        assertFalse(customers.isEmpty());
        System.out.println("All Customers: " + customers);
    }

    @Test
    @Order(6)
    void findByFirstName() {
        customerRepository.save(customer);
        List<Customer> found = customerService.findByFirstName("John");
        assertFalse(found.isEmpty());
        assertEquals("John", found.get(0).getFirstName());
        System.out.println("Found by First Name: " + found);
    }

    @Test
    @Order(7)
    void findByLastName() {
        customerRepository.save(customer);
        List<Customer> found = customerService.findByLastName("Doe");
        assertFalse(found.isEmpty());
        assertEquals("Doe", found.get(0).getLastName());
        System.out.println("Found by Last Name: " + found);
    }

    @Test
    @Order(8)
    void findByUserName() {
        customerRepository.save(customer);
        Customer found = customerService.findByUserName("johnD");
        assertNotNull(found);
        assertEquals("johnD", found.getUserName());
        assertEquals("John", found.getFirstName());
        System.out.println("Found by Username: " + found);
    }

    @Test
    @Order(9)
    void existsByUserName() {
        customerRepository.save(customer);
        boolean exists = customerService.existsByUserName("johnD");
        assertTrue(exists);

        boolean notExists = customerService.existsByUserName("nonexistentUser");
        assertFalse(notExists);
        System.out.println("Exists by Username: " + exists);
    }
}
