package za.ac.cput.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import za.ac.cput.domain.User;
import za.ac.cput.domain.Employee;
import za.ac.cput.domain.Customer;
import za.ac.cput.domain.Admin;
import za.ac.cput.service.EmployeeService;
import za.ac.cput.service.CustomerService;
import za.ac.cput.service.AdminService;
import za.ac.cput.service.JwtService;

import java.io.IOException;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
@Slf4j
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final EmployeeService employeeService;
    private final CustomerService customerService;
    private final AdminService adminService;

    @Autowired
    public JwtAuthenticationFilter(
            JwtService jwtService,
            EmployeeService employeeService,
            CustomerService customerService,
            AdminService adminService
    ) {
        this.jwtService = jwtService;
        this.employeeService = employeeService;
        this.customerService = customerService;
        this.adminService = adminService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        final String authHeader = request.getHeader("Authorization");
        final String jwt;
        final String username;

        if (StringUtils.isEmpty(authHeader) || !StringUtils.startsWith(authHeader, "Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        jwt = authHeader.substring(7);
        username = jwtService.extractUserName(jwt);

        if (StringUtils.isNotEmpty(username) && SecurityContextHolder.getContext().getAuthentication() == null) {

            User user = employeeService.findByUserName(username);
            String userType = "Employee";

            if (user == null) {
                user = customerService.findByUserName(username);
                userType = (user != null) ? "Customer" : userType;
            }

            if (user == null) {
                user = adminService.findByUserName(username);
                userType = (user != null) ? "Admin" : userType;
            }

            if (user != null && jwtService.isTokenValid(jwt, user)) {
                UsernamePasswordAuthenticationToken authToken =
                        new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authToken);

                System.out.println("Authenticated " + userType + ": " + user.getUserName());
            }
        }

        filterChain.doFilter(request, response);
    }
}
