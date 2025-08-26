package za.ac.cput.factory;

import org.junit.jupiter.api.Test;
import za.ac.cput.domain.Address;
import za.ac.cput.domain.Contact;
import za.ac.cput.domain.Employee;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeFactoryTest {

    // Helper method to create a sample Address
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

    // Helper method to create a sample Contact
    private Contact sampleContact() {
        return new Contact.Builder()
                .setPhoneNumber("0211234567")
                .setEmail("jane@example.com")
                .build();
    }

    @Test
    void testCreateValidEmployee() {
        Employee employee = EmployeeFactory.createEmployee(
                sampleAddress(), sampleContact(),
                "Jane", "Smith",
                "Manager", 15.0, "janeS", "pass456", "Employee"
        );
        assertNotNull(employee, "Employee should be created successfully");
        System.out.println("Valid Employee:\n" + employee);
    }

    @Test
    void testCreateEmployeeWithEmptyPosition() {
        Employee employee = EmployeeFactory.createEmployee(
                sampleAddress(), sampleContact(),
                "Jane", "Smith",
                "", 15.0, "janeS", "pass456", "Employee"
        );
        assertNull(employee, "Employee creation should fail with empty position");
    }

    @Test
    void testCreateEmployeeWithNegativeDiscount() {
        Employee employee = EmployeeFactory.createEmployee(
                sampleAddress(), sampleContact(),
                "Jane", "Smith",
                "Manager", -5.0, "janeS", "pass456", "Employee"
        );
        assertNull(employee, "Employee creation should fail with negative discount");
    }

}
