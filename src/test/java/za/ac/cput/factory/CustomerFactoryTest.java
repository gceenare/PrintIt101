package za.ac.cput.factory;

import org.junit.jupiter.api.Test;
import za.ac.cput.domain.Customer;

import static org.junit.jupiter.api.Assertions.*;

class CustomerFactoryTest {

    @Test
    public void testCreateValidCustomer() {
        Customer customer = CustomerFactory.createCustomer(
                1, 100, 200,
                "John", "Doe",
                10.0, "johnD", "pass123", "Customer"
        );
        assertNotNull(customer);
        System.out.println("Valid Customer:\n" + customer);
    }

    @Test
    public void testCreateCustomerWithNullUserId() {
        Customer customer = CustomerFactory.createCustomer(
                0, 100, 200,
                "John", "Doe",
                10.0, "johnD", "pass123", "Customer"
        );
        assertNull(customer);
    }

    @Test
    public void testCreateCustomerWithEmptyFirstName() {
        Customer customer = CustomerFactory.createCustomer(
                1, 100, 200,
                "", "Doe",
                10.0, "johnD", "pass123", "Customer"
        );
        assertNull(customer);
    }

    @Test
    public void testCreateCustomerWithNegativeDiscount() {
        Customer customer = CustomerFactory.createCustomer(
                1, 100, 200,
                "John", "Doe",
                -5.0, "johnD", "pass123", "Customer"
        );
        assertNull(customer);
    }

    @Test
    public void testCreateCompletelyInvalidCustomer() {
        Customer customer = CustomerFactory.createCustomer(
                0, 0, 0,
                "", "",
                -1.0, "", "", ""
        );
        assertNull(customer);
    }
}