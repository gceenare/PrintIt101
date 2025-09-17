package za.ac.cput.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.domain.Address;
import za.ac.cput.domain.Contact;
import za.ac.cput.domain.Customer;
import za.ac.cput.factory.AddressFactory;
import za.ac.cput.factory.ContactFactory;
import za.ac.cput.factory.CustomerFactory;
import za.ac.cput.repository.CustomerRepository;

import java.util.List;

@Service
public class CustomerService implements ICustomerService {

    private final CustomerRepository repository;

    @Autowired
    public CustomerService(CustomerRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional
    public Customer create(Customer customer) {
        // Validate that customer and its address/contact are not null
        if (customer == null || customer.getAddress() == null || customer.getContact() == null) {
            throw new IllegalArgumentException("Customer, address, or contact cannot be null");
        }

        // Create Address using factory
        Address address = AddressFactory.createResidentialAddress(
                customer.getAddress().getPropertyNumber(),
                customer.getAddress().getBuildingName(),
                customer.getAddress().getUnitNumber(),
                customer.getAddress().getStreet(),
                customer.getAddress().getMunicipality(),
                customer.getAddress().getProvince(),
                customer.getAddress().getPostalCode(),
                customer.getAddress().getCountry()
        );

        // Create Contact using factory
        Contact contact = ContactFactory.createContact(
                customer.getContact().getPhoneNumber(),
                customer.getContact().getEmail()
        );

        // Validate factory output
        if (address == null || contact == null) {
            throw new IllegalArgumentException("Invalid address or contact data");
        }

        // Create Customer using factory
        Customer validatedCustomer = CustomerFactory.createCustomer(
                address,
                contact,
                customer.getFirstName(),
                customer.getLastName(),
                customer.getCustomerDiscount(),
                customer.getUserName(),
                customer.getPassword(),
                "CUSTOMER"
        );

        if (validatedCustomer == null) {
            throw new IllegalArgumentException("Invalid customer data");
        }

        return repository.save(validatedCustomer);
    }

    @Override
    public Customer read(Integer id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public Customer update(Customer customer) {
        if (repository.existsById(customer.getUserId())) {
            return repository.save(customer);
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
    public List<Customer> getAll() {
        return repository.findAll();
    }

    @Override
    public List<Customer> findByFirstName(String firstName) {
        return repository.findByFirstNameContainingIgnoreCase(firstName);
    }

    @Override
    public List<Customer> findByLastName(String lastName) {
        return repository.findByLastNameContainingIgnoreCase(lastName);
    }

    @Override
    public Customer findByUserName(String userName) {
        return repository.findByUserName(userName).orElse(null);
    }

    @Override
    public boolean existsByUserName(String userName) {
        return repository.existsByUserName(userName);
    }
}