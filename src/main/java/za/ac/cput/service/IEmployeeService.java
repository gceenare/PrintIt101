package za.ac.cput.service;

import za.ac.cput.domain.Employee;
import java.util.List;

public interface IEmployeeService extends IService<Employee, Integer> {
    List<Employee> getAll();
    List<Employee> findByFirstName(String firstName);
    List<Employee> findByLastName(String lastName);
    List<Employee> findByPosition(String position);
    Employee findByUserName(String userName);
    boolean existsByUserName(String userName);
}
