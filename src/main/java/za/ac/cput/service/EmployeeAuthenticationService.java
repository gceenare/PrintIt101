package za.ac.cput.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import za.ac.cput.domain.Employee;
import za.ac.cput.dto.JwtAuthenticationResponse;
import za.ac.cput.dto.SignInRequest;
import za.ac.cput.repository.EmployeeRepository;

@Service
public class EmployeeAuthenticationService {

    private final EmployeeRepository employeeRepository;
    private final EmployeeService employeeService;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Autowired
    public EmployeeAuthenticationService(EmployeeRepository employeeRepository,
                                         EmployeeService employeeService,
                                         PasswordEncoder passwordEncoder,
                                         JwtService jwtService,
                                         AuthenticationManager authenticationManager) {
        this.employeeRepository = employeeRepository;
        this.employeeService = employeeService;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    public JwtAuthenticationResponse signin(SignInRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );

        Employee employee = employeeRepository.findByUserName(request.getUsername())
                .orElseThrow(() -> new IllegalArgumentException("Invalid ID or password"));

        String jwt = jwtService.generateToken(employee);
        return new JwtAuthenticationResponse.Builder().setToken(jwt).build();
    }
}
