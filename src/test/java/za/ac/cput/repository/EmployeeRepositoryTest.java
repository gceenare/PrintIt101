package za.ac.cput.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import za.ac.cput.domain.Employee;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class EmployeeRepositoryTest {

    @Autowired
    private EmployeeRepository employeeRepository;

    private Employee employee1;
    private Employee employee2;

    @BeforeEach
    void setUp() {
        // Clear any existing data
        employeeRepository.deleteAll();

        // Create entities without setting ID - let JPA auto-generate them
        employee1 = new Employee.Builder()
                .setAddressId(300)
                .setContactId(400)
                .setFirstName("Alice")
                .setLastName("Manager")
                .setUserName("aliceM")
                .setPassword("admin123")
                .setRole("Employee")
                .setPosition("Manager")
                .setStaffDiscount(20.0)
                .build();

        employee2 = new Employee.Builder()
                .setAddressId(301)
                .setContactId(401)
                .setFirstName("Bob")
                .setLastName("Clerk")
                .setUserName("bobC")
                .setPassword("staff456")
                .setRole("Employee")
                .setPosition("Clerk")
                .setStaffDiscount(10.0)
                .build();

        employee1 = employeeRepository.save(employee1);
        employee2 = employeeRepository.save(employee2);
    }

    @Test
    void testFindByUserName() {
        Optional<Employee> found = employeeRepository.findByUserName("aliceM");
        assertTrue(found.isPresent());
        assertEquals("Alice", found.get().getFirstName());
        assertEquals("Manager", found.get().getPosition());
        System.out.println("Found employee by username: " + found.get());
    }

    @Test
    void testFindByUserNameNotFound() {
        Optional<Employee> found = employeeRepository.findByUserName("nonexistent");
        assertFalse(found.isPresent());
        System.out.println("Employee not found for username: nonexistent");
    }

    @Test
    void testFindByPosition() {
        List<Employee> employees = employeeRepository.findByPosition("Manager");
        assertEquals(1, employees.size());
        assertEquals("Alice", employees.get(0).getFirstName());
        System.out.println("Found employees by position 'Manager': " + employees);
    }

    @Test
    void testFindByFirstNameContainingIgnoreCase() {
        List<Employee> employees = employeeRepository.findByFirstNameContainingIgnoreCase("alice");
        assertEquals(1, employees.size());
        assertEquals("Alice", employees.get(0).getFirstName());
        System.out.println("Found employees by first name containing 'alice': " + employees);
    }

    @Test
    void testFindByLastNameContainingIgnoreCase() {
        List<Employee> employees = employeeRepository.findByLastNameContainingIgnoreCase("clerk");
        assertEquals(1, employees.size());
        assertEquals("Clerk", employees.get(0).getLastName());
        System.out.println("Found employees by last name containing 'clerk': " + employees);
    }

    @Test
    void testExistsByUserName() {
        assertTrue(employeeRepository.existsByUserName("aliceM"));
        assertFalse(employeeRepository.existsByUserName("nonexistent"));
        System.out.println("Username 'aliceM' exists: true");
        System.out.println("Username 'nonexistent' exists: false");
    }

    @Test
    void testSaveAndFindById() {
        Employee newEmployee = new Employee.Builder()
                .setAddressId(302)
                .setContactId(402)
                .setFirstName("Carol")
                .setLastName("Supervisor")
                .setUserName("carolS")
                .setPassword("super789")
                .setRole("Employee")
                .setPosition("Supervisor")
                .setStaffDiscount(15.0)
                .build();

        Employee saved = employeeRepository.save(newEmployee);

        Optional<Employee> found = employeeRepository.findById(saved.getUserId());
        assertTrue(found.isPresent());
        assertEquals("Carol", found.get().getFirstName());
        assertEquals("Supervisor", found.get().getPosition());
        System.out.println("Saved and found employee: " + found.get());
    }

    @Test
    void testFindAll() {
        List<Employee> allEmployees = employeeRepository.findAll();
        assertEquals(2, allEmployees.size());
        System.out.println("Total employees found: " + allEmployees.size());
        allEmployees.forEach(emp -> System.out.println("Employee: " + emp.getFirstName() + " " + emp.getLastName()));
    }

    @Test
    void testDeleteById() {
        Optional<Employee> employee = employeeRepository.findByUserName("bobC");
        assertTrue(employee.isPresent());

        employeeRepository.deleteById(employee.get().getUserId());

        Optional<Employee> deletedEmployee = employeeRepository.findById(employee.get().getUserId());
        assertFalse(deletedEmployee.isPresent());
        System.out.println("Employee successfully deleted");
    }
}
