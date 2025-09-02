package za.ac.cput.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.domain.Address;
import za.ac.cput.domain.Contact;
import za.ac.cput.domain.Employee;
import za.ac.cput.factory.AddressFactory;
import za.ac.cput.factory.ContactFactory;
import za.ac.cput.factory.EmployeeFactory;
import za.ac.cput.repository.EmployeeRepository;

import java.util.List;

@Service
public class EmployeeService implements IEmployeeService {

    private final EmployeeRepository repository;

    @Autowired
    public EmployeeService(EmployeeRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional
    public Employee create(Employee employee) {
        // Validate that employee and its address/contact are not null
        if (employee == null || employee.getAddress() == null || employee.getContact() == null) {
            throw new IllegalArgumentException("Employee, address, or contact cannot be null");
        }

        // Create Address using factory
        Address address = AddressFactory.createResidentialAddress(
                employee.getAddress().getPropertyNumber(),
                employee.getAddress().getBuildingName(),
                employee.getAddress().getUnitNumber(),
                employee.getAddress().getStreet(),
                employee.getAddress().getMunicipality(),
                employee.getAddress().getProvince(),
                employee.getAddress().getPostalCode(),
                employee.getAddress().getCountry()
        );

        // Create Contact using factory
        Contact contact = ContactFactory.createContact(
                employee.getContact().getPhoneNumber(),
                employee.getContact().getEmail()
        );

        // Validate factory output
        if (address == null || contact == null) {
            throw new IllegalArgumentException("Invalid address or contact data");
        }

        // Create Employee using factory
        Employee validatedEmployee = EmployeeFactory.createEmployee(
                address,
                contact,
                employee.getFirstName(),
                employee.getLastName(),
                employee.getPosition(),
                employee.getStaffDiscount(),
                employee.getUserName(),
                employee.getPassword(),
                "EMPLOYEE"
        );

        if (validatedEmployee == null) {
            throw new IllegalArgumentException("Invalid employee data");
        }

        return repository.save(validatedEmployee);
    }

    @Override
    public Employee read(Integer id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public Employee update(Employee employee) {
        if (repository.existsById(employee.getUserId())) {
            return repository.save(employee);
        }
        return null;
    }

    @Override
    public boolean delete(Integer id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public List<Employee> getAll() {
        return repository.findAll();
    }

    @Override
    public List<Employee> findByFirstName(String firstName) {
        return repository.findByFirstNameContainingIgnoreCase(firstName);
    }

    @Override
    public List<Employee> findByLastName(String lastName) {
        return repository.findByLastNameContainingIgnoreCase(lastName);
    }

    @Override
    public List<Employee> findByPosition(String position) {
        return repository.findByPosition(position);
    }

    @Override
    public Employee findByUserName(String userName) {
        return repository.findByUserName(userName).orElse(null);
    }

    @Override
    public boolean existsByUserName(String userName) {
        return repository.existsByUserName(userName);
    }
}