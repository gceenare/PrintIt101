package za.ac.cput.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import za.ac.cput.domain.Customer;
import za.ac.cput.dto.JwtAuthenticationResponse;
import za.ac.cput.dto.SignInRequest;
import za.ac.cput.repository.CustomerRepository;

@Service
public class CustomerAuthenticationService {

    private final CustomerRepository customerRepository;
    private final CustomerService customerService;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Autowired
    public CustomerAuthenticationService(CustomerRepository customerRepository,
                                         CustomerService customerService,
                                         PasswordEncoder passwordEncoder,
                                         JwtService jwtService,
                                         AuthenticationManager authenticationManager) {
        this.customerRepository = customerRepository;
        this.customerService = customerService;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    public JwtAuthenticationResponse signin(SignInRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );

        Customer customer = customerRepository.findByUserName(request.getUsername())
                .orElseThrow(() -> new IllegalArgumentException("Invalid ID or password"));

        String jwt = jwtService.generateToken(customer);
        return new JwtAuthenticationResponse.Builder().setToken(jwt).build();
    }
}
