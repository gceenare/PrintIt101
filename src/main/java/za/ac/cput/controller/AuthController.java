package za.ac.cput.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.domain.Customer;
import za.ac.cput.domain.Contact;
import za.ac.cput.domain.Address;
import za.ac.cput.factory.ContactFactory;
import za.ac.cput.factory.CustomerFactory;
import za.ac.cput.service.CustomerService;
import za.ac.cput.util.ErrorResponse;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private CustomerService customerService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        try {
            Customer customer = customerService.findByUserName(loginRequest.getUserName());
            if (customer != null && customer.getPassword().equals(loginRequest.getPassword())) {
                return ResponseEntity.ok(customer);
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

        Address address = new Address.Builder()
                .setPropertyNumber(123)
                .setStreet(registerRequest.getAddress().getStreet())
                .setMunicipality(registerRequest.getAddress().getMunicipality())
                .setProvince(registerRequest.getAddress().getProvince())
                .setPostalCode(registerRequest.getAddress().getPostalCode())
                .setCountry(registerRequest.getAddress().getCountry())
                .build();

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
        private String street;
        private String municipality;
        private String province;
        private String postalCode;
        private String country;
        public AddressRequest() {}
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