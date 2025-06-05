package za.ac.cput.factory;

import org.junit.jupiter.api.Test;
import za.ac.cput.domain.Employee;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeFactoryTest {

    @Test
    public void testCreateValidEmployee() {
        Employee employee = EmployeeFactory.createEmployee(
                1, 200, 300,
                "Jane", "Smith",
                "Manager", 15.0, "janeS", "pass456", "Employee"
        );
        assertNotNull(employee);
        System.out.println("Valid Employee:\n" + employee);
    }

    @Test
    public void testCreateEmployeeWithEmptyPosition() {
        Employee employee = EmployeeFactory.createEmployee(
                1, 200, 300,
                "Jane", "Smith",
                "", 15.0, "janeS", "pass456", "Employee"
        );
        assertNull(employee);
    }

    @Test
    public void testCreateEmployeeWithNegativeDiscount() {
        Employee employee = EmployeeFactory.createEmployee(
                1,  200, 300,
                "Jane", "Smith",
                "Manager", -5.0, "janeS", "pass456", "Employee"
        );
        assertNull(employee);
    }

    @Test
    public void testCreateCompletelyInvalidEmployee() {
        Employee employee = EmployeeFactory.createEmployee(
                0, 0, 0,
                "", "",
                "", -1.0, "", "", ""
        );
        assertNull(employee);
    }
}
