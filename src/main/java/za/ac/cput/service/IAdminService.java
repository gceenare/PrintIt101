/* IAdminService.java
   Admin Service Interface
   Author: System Generated
   Date: 16 September 2025
*/

package za.ac.cput.service;

import za.ac.cput.domain.Admin;
import za.ac.cput.domain.Customer;
import java.util.List;

public interface IAdminService extends IService<Admin, Integer> {
    List<Admin> getAll();
    List<Admin> findByFirstName(String firstName);
    List<Admin> findByLastName(String lastName);
    List<Admin> findByAdminLevel(String adminLevel);
    List<Admin> findByDepartment(String department);
    List<Admin> findByAdminLevelAndDepartment(String adminLevel, String department);
    Admin findByUserName(String userName);
    boolean existsByUserName(String userName);

    // Customer management methods
    List<Customer> getAllCustomers();
    Customer getCustomerById(Integer customerId);
    Customer updateCustomer(Customer customer);
    boolean deleteCustomer(Integer customerId);
    List<Customer> searchCustomersByName(String name);
    Customer activateCustomer(Integer customerId);
    Customer deactivateCustomer(Integer customerId);
}