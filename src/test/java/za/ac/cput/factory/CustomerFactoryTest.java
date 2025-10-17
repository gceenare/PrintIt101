package za.ac.cput.factory;

import org.junit.jupiter.api.Test;
import za.ac.cput.domain.Address;
import za.ac.cput.domain.Contact;
import za.ac.cput.domain.Customer;

import static org.junit.jupiter.api.Assertions.*;

class CustomerFactoryTest {

    private Address sampleAddress() {
        return new Address.Builder()
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

    @Test
    void testCreateValidCustomer() {
        Customer customer = CustomerFactory.createCustomer(
                sampleAddress(), sampleContact(),
                "John", "Doe",
                10.0, "johnD", "pass123", "Customer"
        );
        assertNotNull(customer);
        System.out.println("Valid Customer:\n" + customer);
    }

    @Test
    void testCreateCustomerWithEmptyFirstName() {
        Customer customer = CustomerFactory.createCustomer(
                sampleAddress(), sampleContact(),
                "", "Doe",
                10.0, "johnD", "pass123", "Customer"
        );
        assertNull(customer);
    }

    @Test
    void testCreateCustomerWithNegativeDiscount() {
        Customer customer = CustomerFactory.createCustomer(
                sampleAddress(), sampleContact(),
                "John", "Doe",
                -5.0, "johnD", "pass123", "Customer"
        );
        assertNull(customer);
    }

    @Test
    void testCreateCompletelyInvalidCustomer() {
        Customer customer = CustomerFactory.createCustomer(
                null, null,
                "", "",
                -1.0, "", "", ""
        );
        assertNull(customer);
    }
}
