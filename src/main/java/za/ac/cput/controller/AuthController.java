package za.ac.cput.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.domain.Admin;
import za.ac.cput.domain.Customer;
import za.ac.cput.domain.Employee;
import za.ac.cput.domain.User;
import za.ac.cput.service.AdminService;
import za.ac.cput.service.CustomerService;
import za.ac.cput.service.EmployeeService;
import za.ac.cput.service.JwtService;
import za.ac.cput.util.ErrorResponse;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private AdminService adminService;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        try {
            User user = null;

            // Check Customer
            Customer customer = customerService.findByUserName(loginRequest.getUserName());
            if (customer != null && passwordEncoder.matches(loginRequest.getPassword(), customer.getPassword())) {
                user = customer;
            }

            // Check Admin
            Admin admin = adminService.findByUserName(loginRequest.getUserName());
            if (admin != null && passwordEncoder.matches(loginRequest.getPassword(), admin.getPassword())) {
                user = admin;
            }

            // Check Employee
            Employee employee = employeeService.findByUserName(loginRequest.getUserName());
            if (employee != null && passwordEncoder.matches(loginRequest.getPassword(), employee.getPassword())) {
                user = employee;
            }

            if (user == null) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                        .body(new ErrorResponse("Invalid username or password"));
            }

            String token = jwtService.generateToken(user);

            Map<String, Object> response = new HashMap<>();
            response.put("userId", user.getUserId());
            response.put("firstName", user.getFirstName());
            response.put("lastName", user.getLastName());
            response.put("userName", user.getUserName());
            response.put("role", user.getRole());
            response.put("token", token);

            // Include extra fields for specific types
            if (user instanceof Customer) {
                response.put("customerDiscount", ((Customer) user).getCustomerDiscount());
                response.put("contact", ((Customer) user).getContact());
                response.put("address", ((Customer) user).getAddress());
            } else if (user instanceof Admin) {
                response.put("adminLevel", ((Admin) user).getAdminLevel());
                response.put("department", ((Admin) user).getDepartment());
                response.put("permissions", ((Admin) user).getPermissions());
            } else if (user instanceof Employee) {
                response.put("position", ((Employee) user).getPosition());
                response.put("staffDiscount", ((Employee) user).getStaffDiscount());
            }

            return ResponseEntity.ok(response);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ErrorResponse("Login failed: " + e.getMessage()));
        }
    }

    // DTO for login
    public static class LoginRequest {
        private String userName;
        private String password;

        public LoginRequest() {}
        public String getUserName() { return userName; }
        public void setUserName(String userName) { this.userName = userName; }
        public String getPassword() { return password; }
        public void setPassword(String password) { this.password = password; }
    }
}
