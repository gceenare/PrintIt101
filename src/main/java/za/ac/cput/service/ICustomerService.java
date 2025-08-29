package za.ac.cput.service;

import za.ac.cput.domain.Customer;
import java.util.List;

public interface ICustomerService extends IService<Customer, Integer> {
    List<Customer> getAll();
    List<Customer> findByFirstName(String firstName);
    List<Customer> findByLastName(String lastName);
    Customer findByUserName(String userName);
    boolean existsByUserName(String userName);
}
