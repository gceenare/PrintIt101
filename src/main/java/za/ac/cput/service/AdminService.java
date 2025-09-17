/* AdminService.java
   Admin Service Implementation
   Author: System Generated
   Date: 16 September 2025
*/

package za.ac.cput.service;

import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.domain.Admin;
import za.ac.cput.domain.Address;
import za.ac.cput.domain.Contact;
import za.ac.cput.domain.Customer;
import za.ac.cput.factory.AddressFactory;
import za.ac.cput.factory.AdminFactory;
import za.ac.cput.factory.ContactFactory;
import za.ac.cput.repository.AdminRepository;
import za.ac.cput.repository.CustomerRepository;

import java.util.List;
import java.util.stream.Stream;

@Service
public class AdminService implements IAdminService {

    private final AdminRepository adminRepository;
    private final CustomerRepository customerRepository;

    @Autowired
    public AdminService(AdminRepository adminRepository, CustomerRepository customerRepository) {
        this.adminRepository = adminRepository;
        this.customerRepository = customerRepository;
    }

    @Override
    @Transactional
    public Admin create(Admin admin) {
        // Validate that admin and its address/contact are not null
        if (admin == null || admin.getAddress() == null || admin.getContact() == null) {
            throw new IllegalArgumentException("Admin, address, or contact cannot be null");
        }

        // Create Address using factory
        Address address = AddressFactory.createResidentialAddress(
                admin.getAddress().getPropertyNumber(),
                admin.getAddress().getBuildingName(),
                admin.getAddress().getUnitNumber(),
                admin.getAddress().getStreet(),
                admin.getAddress().getMunicipality(),
                admin.getAddress().getProvince(),
                admin.getAddress().getPostalCode(),
                admin.getAddress().getCountry()
        );

        // Create Contact using factory
        Contact contact = ContactFactory.createContact(
                admin.getContact().getPhoneNumber(),
                admin.getContact().getEmail()
        );

        // Validate factory output
        if (address == null || contact == null) {
            throw new IllegalArgumentException("Invalid address or contact data");
        }

        // Create Admin using factory
        Admin validatedAdmin = AdminFactory.createAdmin(
                address,
                contact,
                admin.getFirstName(),
                admin.getLastName(),
                admin.getAdminLevel(),
                admin.getDepartment(),
                admin.getPermissions(),
                admin.getUserName(),
                admin.getPassword(),
                "ADMIN"
        );

        if (validatedAdmin == null) {
            throw new IllegalArgumentException("Invalid admin data");
        }

        return adminRepository.save(validatedAdmin);
    }

    @Override
    public Admin read(Integer id) {
        return adminRepository.findById(id).orElse(null);
    }

    @Override
    public Admin update(Admin admin) {
        if (adminRepository.existsById(admin.getUserId())) {
            return adminRepository.save(admin);
        }
        return null;
    }

    @Override
    public boolean delete(Integer id) {
        if (adminRepository.existsById(id)) {
            adminRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public List<Admin> getAll() {
        return adminRepository.findAll();
    }

    @Override
    public List<Admin> findByFirstName(String firstName) {
        return adminRepository.findByFirstNameContainingIgnoreCase(firstName);
    }

    @Override
    public List<Admin> findByLastName(String lastName) {
        return adminRepository.findByLastNameContainingIgnoreCase(lastName);
    }

    @Override
    public List<Admin> findByAdminLevel(String adminLevel) {
        return adminRepository.findByAdminLevel(adminLevel);
    }

    @Override
    public List<Admin> findByDepartment(String department) {
        return adminRepository.findByDepartment(department);
    }

    @Override
    public List<Admin> findByAdminLevelAndDepartment(String adminLevel, String department) {
        return adminRepository.findByAdminLevelAndDepartment(adminLevel, department);
    }

    @Override
    public Admin findByUserName(String userName) {
        return adminRepository.findByUserName(userName).orElse(null);
    }

    @Override
    public boolean existsByUserName(String userName) {
        return adminRepository.existsByUserName(userName);
    }

    // Customer Management Methods
    @Override
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public Customer getCustomerById(Integer customerId) {
        return customerRepository.findById(customerId).orElse(null);
    }

    @Override
    @Transactional
    public Customer updateCustomer(Customer customer) {
        if (customer != null && customerRepository.existsById(customer.getUserId())) {
            return customerRepository.save(customer);
        }
        return null;
    }

    @Override
    @Transactional
    public boolean deleteCustomer(Integer customerId) {
        if (customerRepository.existsById(customerId)) {
            customerRepository.deleteById(customerId);
            return true;
        }
        return false;
    }

    @Override
    public List<Customer> searchCustomersByName(String name) {
        // Search by both first name and last name
        List<Customer> byFirstName = customerRepository.findByFirstNameContainingIgnoreCase(name);
        List<Customer> byLastName = customerRepository.findByLastNameContainingIgnoreCase(name);

        return Stream.concat(byFirstName.stream(), byLastName.stream())
                .distinct()
                .toList();
    }

    @Override
    @Transactional
    public Customer activateCustomer(Integer customerId) {
        Customer customer = customerRepository.findById(customerId).orElse(null);
        if (customer != null) {
            // Assuming we add an 'active' field to Customer in the future
            // For now, we'll just return the customer as-is
            // customer.setActive(true);
            return customerRepository.save(customer);
        }
        return null;
    }

    @Override
    @Transactional
    public Customer deactivateCustomer(Integer customerId) {
        Customer customer = customerRepository.findById(customerId).orElse(null);
        if (customer != null) {
            // Assuming we add an 'active' field to Customer in the future
            // For now, we'll just return the customer as-is
            // customer.setActive(false);
            return customerRepository.save(customer);
        }
        return null;
    }




    @PostConstruct
    public void createSuperAdmin() {
        String superAdminUsername = "superadmin";

        if (!existsByUserName(superAdminUsername)) {
            Address address = AddressFactory.createResidentialAddress(
                    0, "Super Admin Building", 0, "Super Street",
                    "Cape Town", "Western Cape", "8000", "South Africa"
            );

            Contact contact = ContactFactory.createContact(
                    "0000000000", "superadmin@example.com"
            );

            Admin superAdmin = AdminFactory.createAdmin(
                    address,
                    contact,
                    "Super",
                    "Admin",
                    "SUPER_ADMIN",
                    "SYSTEM",
                    "ALL_PERMISSIONS",
                    superAdminUsername,
                    "supersecretpassword",
                    "ADMIN" // or role = "SUPER_ADMIN"
            );

            create(superAdmin);
            System.out.println("Super Admin created: " + superAdminUsername);
        }
    }
}