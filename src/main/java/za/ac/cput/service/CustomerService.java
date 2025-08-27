package za.ac.cput.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.domain.Customer;
import za.ac.cput.repository.CustomerRepository;

import java.util.List;

@Service
public class CustomerService implements ICustomerService {

    private CustomerRepository repository;

    @Autowired
    public CustomerService(CustomerRepository repository) {
        this.repository = repository;
    }
//use same JPA method to save and update
    @Override
    public Customer create(Customer customer) {
        return repository.save(customer);
    }

    @Override
    public Customer read(Integer id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public Customer update(Customer customer) {
        return repository.save(customer);
    }

    @Override
    public boolean delete(Integer id) {
        repository.deleteById(id);
        return true;
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
