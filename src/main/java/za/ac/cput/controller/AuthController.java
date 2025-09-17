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

        if (customerService.existsByUserName(registerRequest.getUserName())) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(new ErrorResponse("Username already exists"));
        }

        Contact contact = ContactFactory.createContact(
                registerRequest.getContact().getPhone(),
                registerRequest.getContact().getEmail()
        );

        System.out.println("DEBUG - Received propertyNumber: " + registerRequest.getAddress().getPropertyNumber());
        System.out.println("DEBUG - Received poBoxNumber: " + registerRequest.getAddress().getPoBoxNumber());
        System.out.println("DEBUG - Received unitNumber: " + registerRequest.getAddress().getUnitNumber());

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

        System.out.println("DEBUG - Created address propertyNumber: " + address.getPropertyNumber());
        System.out.println("DEBUG - Created address poBoxNumber: " + address.getPoBoxNumber());
        System.out.println("DEBUG - Created address unitNumber: " + address.getUnitNumber());

        Customer newCustomer = CustomerFactory.createCustomer(
                address,
                contact,
                registerRequest.getFirstName(),
                registerRequest.getLastName(),
                0.1,
                registerRequest.getUserName(),
                registerRequest.getPassword(),
                "CUSTOMER"
        );

        if (newCustomer == null) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ErrorResponse("Failed to create customer entity"));
        }

        Customer savedCustomer = customerService.create(newCustomer);

        // Debug what was actually saved to the database
        System.out.println("DEBUG - Saved customer address propertyNumber: " + savedCustomer.getAddress().getPropertyNumber());
        System.out.println("DEBUG - Saved customer address poBoxNumber: " + savedCustomer.getAddress().getPoBoxNumber());
        System.out.println("DEBUG - Saved customer address unitNumber: " + savedCustomer.getAddress().getUnitNumber());

        return ResponseEntity.status(HttpStatus.CREATED).body(savedCustomer);

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