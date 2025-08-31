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
import za.ac.cput.domain.Employee;
import za.ac.cput.factory.EmployeeFactory;
import za.ac.cput.repository.EmployeeRepository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@Rollback
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class EmployeeServiceTest {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private EmployeeRepository employeeRepository;

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
        // Use timestamp to ensure unique username for each test run
        String uniqueUsername = "janeS" + System.currentTimeMillis();
        employee = EmployeeFactory.createEmployee(
                sampleAddress(), sampleContact(),
                "Jane", "Smith",
                "Manager", 15.0, uniqueUsername, "pass456", "Employee"
        );
    }

    @Test
    @Order(1)
    void create() {
        Employee saved = employeeService.create(employee);
        assertNotNull(saved);
        assertEquals("Jane", saved.getFirstName());
        assertEquals("Smith", saved.getLastName());
        assertEquals(employee.getUserName(), saved.getUserName());
        assertEquals("Manager", saved.getPosition());
        assertEquals(15.0, saved.getStaffDiscount());
        assertTrue(saved.getUserId() > 0);
        System.out.println("Created Employee: " + saved);
    }

    @Test
    @Order(2)
    void read() {
        Employee saved = employeeRepository.save(employee);
        Employee found = employeeService.read(saved.getUserId());
        assertNotNull(found);
        assertEquals("Jane", found.getFirstName());
        assertEquals("Smith", found.getLastName());
        assertEquals(saved.getUserId(), found.getUserId());
        System.out.println("Read Employee: " + found);
    }

    @Test
    @Order(3)
    void update() {
        Employee saved = employeeRepository.save(employee);
        Employee updatedEmployee = new Employee.Builder()
                .copy(saved)
                .setFirstName("John")
                .setLastName("Doe")
                .setPosition("Senior Manager")
                .setStaffDiscount(20.0)
                .build();
        Employee updated = employeeService.update(updatedEmployee);
        assertNotNull(updated);
        assertEquals("John", updated.getFirstName());
        assertEquals("Doe", updated.getLastName());
        assertEquals("Senior Manager", updated.getPosition());
        assertEquals(20.0, updated.getStaffDiscount());
        assertEquals(saved.getUserId(), updated.getUserId());
        System.out.println("Updated Employee: " + updated);
    }

    @Test
    @Order(4)
    void delete() {
        Employee saved = employeeRepository.save(employee);
        boolean deleted = employeeService.delete(saved.getUserId());
        assertTrue(deleted);
        Employee found = employeeService.read(saved.getUserId());
        assertNull(found);
        System.out.println("Employee deleted successfully");
    }

    @Test
    @Order(5)
    void getAll() {
        employeeService.create(employee);
        List<Employee> employees = employeeService.getAll();
        assertNotNull(employees);
        assertFalse(employees.isEmpty());
        System.out.println("All Employees: " + employees);
    }

    @Test
    @Order(6)
    void findByFirstName() {
        employeeRepository.save(employee);
        List<Employee> found = employeeService.findByFirstName("Jane");
        assertFalse(found.isEmpty());
        assertEquals("Jane", found.get(0).getFirstName());
        System.out.println("Found by First Name: " + found);
    }

    @Test
    @Order(7)
    void findByLastName() {
        employeeRepository.save(employee);
        List<Employee> found = employeeService.findByLastName("Smith");
        assertFalse(found.isEmpty());
        assertEquals("Smith", found.get(0).getLastName());
        System.out.println("Found by Last Name: " + found);
    }

    @Test
    @Order(8)
    void findByPosition() {
        employeeRepository.save(employee);
        List<Employee> found = employeeService.findByPosition("Manager");
        assertFalse(found.isEmpty());
        assertEquals("Manager", found.get(0).getPosition());
        System.out.println("Found by Position: " + found);
    }

    @Test
    @Order(9)
    void findByUserName() {
        Employee saved = employeeRepository.save(employee);
        Employee found = employeeService.findByUserName(saved.getUserName());
        assertNotNull(found);
        assertEquals(saved.getUserName(), found.getUserName());
        assertEquals("Jane", found.getFirstName());
        System.out.println("Found by Username: " + found);
    }

    @Test
    @Order(10)
    void existsByUserName() {
        Employee saved = employeeRepository.save(employee);
        boolean exists = employeeService.existsByUserName(saved.getUserName());
        assertTrue(exists);

        boolean notExists = employeeService.existsByUserName("nonexistentUser");
        assertFalse(notExists);
        System.out.println("Exists by Username: " + exists);
    }
}
