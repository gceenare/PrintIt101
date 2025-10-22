package za.ac.cput.service;

import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
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
public class AdminService implements IAdminService, UserDetailsService {

    private final AdminRepository adminRepository;
    private final CustomerRepository customerRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AdminService(AdminRepository adminRepository, CustomerRepository customerRepository, PasswordEncoder passwordEncoder) {
        this.adminRepository = adminRepository;
        this.customerRepository = customerRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // --- Admin CRUD (unchanged) ---
    @Override
    @Transactional
    public Admin create(Admin admin) {
        if (admin == null || admin.getAddress() == null || admin.getContact() == null) {
            throw new IllegalArgumentException("Admin, address, or contact cannot be null");
        }

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

        Contact contact = ContactFactory.createContact(
                admin.getContact().getPhoneNumber(),
                admin.getContact().getEmail()
        );

        if (address == null || contact == null) {
            throw new IllegalArgumentException("Invalid address or contact data");
        }

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
    @Transactional
    public Admin update(Admin admin) {
        if (admin != null && admin.getUserId() > 0) {
            if (admin.getPassword() != null && !admin.getPassword().startsWith("$2a$")) {
                admin = new Admin.Builder().copy(admin)
                        .setPassword(passwordEncoder.encode(admin.getPassword()))
                        .build();
            }
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
        return List.of();
    }

    @Override
    public List<Admin> findByLastName(String lastName) {
        return List.of();
    }

    @Override
    public List<Admin> findByAdminLevel(String adminLevel) {
        return List.of();
    }

    @Override
    public List<Admin> findByDepartment(String department) {
        return List.of();
    }

    @Override
    public List<Admin> findByAdminLevelAndDepartment(String adminLevel, String department) {
        return List.of();
    }

    @Override
    public Admin findByUserName(String userName) {
        return adminRepository.findByUserName(userName).orElse(null);
    }

    @Override
    public boolean existsByUserName(String userName) {
        return adminRepository.existsByUserName(userName);
    }

    @Override
    public List<Customer> getAllCustomers() {
        return List.of();
    }

    @Override
    public Customer getCustomerById(Integer customerId) {
        return null;
    }

    @Override
    public Customer updateCustomer(Customer customer) {
        return null;
    }

    @Override
    public boolean deleteCustomer(Integer customerId) {
        return false;
    }

    @Override
    public List<Customer> searchCustomersByName(String name) {
        return List.of();
    }

    @Override
    public Customer activateCustomer(Integer customerId) {
        return null;
    }

    @Override
    public Customer deactivateCustomer(Integer customerId) {
        return null;
    }

    // --- Implement UserDetailsService for Spring Security ---
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Admin admin = adminRepository.findByUserName(username)
                .orElseThrow(() -> new UsernameNotFoundException("Admin not found: " + username));
        return admin; // Admin must implement UserDetails (or return a custom UserDetails object)
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

            String encodedPassword = passwordEncoder.encode("supersecretpassword");

            Admin superAdmin = AdminFactory.createAdmin(
                    address,
                    contact,
                    "Super",
                    "Admin",
                    "SUPER_ADMIN",
                    "SYSTEM",
                    "ALL_PERMISSIONS",
                    superAdminUsername,
                    encodedPassword,
                    "ADMIN"
            );

            create(superAdmin);
            System.out.println("Super Admin created: " + superAdminUsername);
        }
    }
}
