package za.ac.cput.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.domain.Customer;
import za.ac.cput.domain.Admin;
import za.ac.cput.domain.Contact;
import za.ac.cput.domain.Address;
import za.ac.cput.factory.ContactFactory;
import za.ac.cput.factory.CustomerFactory;
import za.ac.cput.service.CustomerService;
import za.ac.cput.service.AdminService;
import za.ac.cput.util.ErrorResponse;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private AdminService adminService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        try {
            // Try customer login first
            Customer customer = customerService.findByUserName(loginRequest.getUserName());
            if (customer != null && customer.getPassword().equals(loginRequest.getPassword())) {
                return ResponseEntity.ok(customer);
            }

            // Try admin login if customer login fails
            Admin admin = adminService.findByUserName(loginRequest.getUserName());
            if (admin != null && admin.getPassword().equals(loginRequest.getPassword())) {
                return ResponseEntity.ok(admin);
            }

            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new ErrorResponse("Invalid username or password"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ErrorResponse("Login failed: " + e.getMessage()));
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest registerRequest) {
        try {
            // Check for existing username first
            if (customerService.existsByUserName(registerRequest.getUserName())) {
                return ResponseEntity.status(HttpStatus.CONFLICT)
                        .body(new ErrorResponse("Username already exists"));
            }

            // Create Contact - will be persisted via cascade
            Contact contact = ContactFactory.createContact(
                    registerRequest.getContact().getPhone(),
                    registerRequest.getContact().getEmail()
            );

            // Create Address - will be persisted via cascade
            Address address = new Address.Builder()
                    .setPropertyNumber(registerRequest.getAddress().getPropertyNumber())
                    .setStreet(registerRequest.getAddress().getStreet())
                    .setMunicipality(registerRequest.getAddress().getMunicipality())
                    .setProvince(registerRequest.getAddress().getProvince())
                    .setPostalCode(registerRequest.getAddress().getPostalCode())
                    .setCountry(registerRequest.getAddress().getCountry())
                    .setBuildingName(registerRequest.getAddress().getBuildingName())
                    .setUnitNumber(registerRequest.getAddress().getUnitNumber())
                    .setPoBoxNumber(registerRequest.getAddress().getPoBoxNumber())
                    .build();

            // Create new Customer without setting ID
            Customer newCustomer = new Customer.Builder()
                    .setAddress(address)
                    .setContact(contact)
                    .setFirstName(registerRequest.getFirstName())
                    .setLastName(registerRequest.getLastName())
                    .setUserName(registerRequest.getUserName())
                    .setPassword(registerRequest.getPassword())
                    .setRole("CUSTOMER")
                    .setCustomerDiscount(0.1)
                    .build();

            // Let service handle persistence
            Customer savedCustomer = customerService.create(newCustomer);

            if (savedCustomer == null || savedCustomer.getUserId() == null) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body(new ErrorResponse("Failed to create customer record"));
            }

            return ResponseEntity.status(HttpStatus.CREATED).body(savedCustomer);

        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ErrorResponse("Invalid input: " + e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ErrorResponse("Registration failed: " + e.getMessage()));
        }
    }


    public static class LoginRequest {
        private String userName;
        private String password;
        public LoginRequest() {}
        public LoginRequest(String userName, String password) {
            this.userName = userName;
            this.password = password;
        }
        public String getUserName() { return userName; }
        public void setUserName(String userName) { this.userName = userName; }
        public String getPassword() { return password; }
        public void setPassword(String password) { this.password = password; }
    }

    public static class RegisterRequest {
        private String firstName;
        private String lastName;
        private String userName;
        private String password;
        private ContactRequest contact;
        private AddressRequest address;
        public String getFirstName() { return firstName; }
        public void setFirstName(String firstName) { this.firstName = firstName; }
        public String getLastName() { return lastName; }
        public void setLastName(String lastName) { this.lastName = lastName; }
        public String getUserName() { return userName; }
        public void setUserName(String userName) { this.userName = userName; }
        public String getPassword() { return password; }
        public void setPassword(String password) { this.password = password; }
        public ContactRequest getContact() { return contact; }
        public void setContact(ContactRequest contact) { this.contact = contact; }
        public AddressRequest getAddress() { return address; }
        public void setAddress(AddressRequest address) { this.address = address; }
    }

    public static class ContactRequest {
        private String email;
        private String phone;
        public ContactRequest() {}
        public String getEmail() { return email; }
        public void setEmail(String email) { this.email = email; }
        public String getPhone() { return phone; }
        public void setPhone(String phone) { this.phone = phone; }
    }

    public static class AddressRequest {
        private String buildingName;
        private int unitNumber;
        private int propertyNumber;
        private int poBoxNumber;
        private String street;
        private String municipality;
        private String province;
        private String postalCode;
        private String country;

        public AddressRequest() {}

        public String getBuildingName() { return buildingName; }
        public void setBuildingName(String buildingName) { this.buildingName = buildingName; }
        public int getUnitNumber() { return unitNumber; }
        public void setUnitNumber(int unitNumber) { this.unitNumber = unitNumber; }
        public int getPropertyNumber() { return propertyNumber; }
        public void setPropertyNumber(int propertyNumber) { this.propertyNumber = propertyNumber; }
        public int getPoBoxNumber() { return poBoxNumber; }
        public void setPoBoxNumber(int poBoxNumber) { this.poBoxNumber = poBoxNumber; }
        public String getStreet() { return street; }
        public void setStreet(String street) { this.street = street; }
        public String getMunicipality() { return municipality; }
        public void setMunicipality(String municipality) { this.municipality = municipality; }
        public String getProvince() { return province; }
        public void setProvince(String province) { this.province = province; }
        public String getPostalCode() { return postalCode; }
        public void setPostalCode(String postalCode) { this.postalCode = postalCode; }
        public String getCountry() { return country; }
        public void setCountry(String country) { this.country = country; }
    }
}