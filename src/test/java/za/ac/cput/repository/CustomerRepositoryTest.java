package za.ac.cput.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import za.ac.cput.domain.Customer;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class CustomerRepositoryTest {

    @Autowired
    private CustomerRepository customerRepository;

    @BeforeEach
    void setUp() {

        Customer customer1 = new Customer.Builder()
                .setAddressId(100)
                .setContactId(200)
                .setFirstName("John")
                .setLastName("Doe")
                .setUserName("johnD")
                .setPassword("pass123")
                .setRole("Customer")
                .setCustomerDiscount(10.0)
                .build();

        Customer customer2 = new Customer.Builder()
                .setAddressId(101)
                .setContactId(201)
                .setFirstName("Jane")
                .setLastName("Smith")
                .setUserName("janeS")
                .setPassword("pass456")
                .setRole("Customer")
                .setCustomerDiscount(15.0)
                .build();

        customer1 = customerRepository.save(customer1);
        customer2 = customerRepository.save(customer2);
    }

    @Test
    void testFindByUserName() {
        Optional<Customer> found = customerRepository.findByUserName("johnD");
        assertTrue(found.isPresent());
        assertEquals("John", found.get().getFirstName());
        System.out.println("Found customer by username: " + found.get());
    }

    @Test
    void testFindByUserNameNotFound() {
        Optional<Customer> found = customerRepository.findByUserName("nonexistent");
        assertFalse(found.isPresent());
        System.out.println("Customer not found for username: nonexistent");
    }

    @Test
    void testFindByFirstNameContainingIgnoreCase() {
        List<Customer> customers = customerRepository.findByFirstNameContainingIgnoreCase("john");
        assertEquals(1, customers.size());
        assertEquals("John", customers.get(0).getFirstName());
        System.out.println("Found customers by first name containing 'john': " + customers);
    }

    @Test
    void testFindByLastNameContainingIgnoreCase() {
        List<Customer> customers = customerRepository.findByLastNameContainingIgnoreCase("smith");
        assertEquals(1, customers.size());
        assertEquals("Smith", customers.get(0).getLastName());
        System.out.println("Found customers by last name containing 'smith': " + customers);
    }

    @Test
    void testExistsByUserName() {
        assertTrue(customerRepository.existsByUserName("johnD"));
        assertFalse(customerRepository.existsByUserName("nonexistent"));
        System.out.println("Username 'johnD' exists: true");
        System.out.println("Username 'nonexistent' exists: false");
    }

    @Test
    void testSaveAndFindById() {
        Customer newCustomer = new Customer.Builder()
                .setAddressId(102)
                .setContactId(202)
                .setFirstName("Bob")
                .setLastName("Johnson")
                .setUserName("bobJ")
                .setPassword("pass789")
                .setRole("Customer")
                .setCustomerDiscount(5.0)
                .build();

        Customer saved = customerRepository.save(newCustomer);

        Optional<Customer> found = customerRepository.findById(saved.getUserId());
        assertTrue(found.isPresent());
        assertEquals("Bob", found.get().getFirstName());
        System.out.println("Saved and found customer: " + found.get());
    }
}
