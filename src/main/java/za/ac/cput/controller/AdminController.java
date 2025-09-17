/* AdminController.java
   Admin REST Controller
   Author: System Generated
   Date: 16 September 2025
*/

package za.ac.cput.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.domain.Admin;
import za.ac.cput.domain.Customer;
import za.ac.cput.service.AdminService;
import za.ac.cput.util.ErrorResponse;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
@CrossOrigin(origins = "*")
public class AdminController {

    private final AdminService adminService;

    @Autowired
    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    // Admin CRUD Operations
    @PostMapping
    public ResponseEntity<?> create(@RequestBody Admin admin) {
        try {
            Admin created = adminService.create(admin);
            if (created == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(new ErrorResponse("Failed to create admin"));
            }
            return ResponseEntity.status(HttpStatus.CREATED).body(created);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ErrorResponse("Validation error: " + e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ErrorResponse("Error creating admin: " + e.getMessage()));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> read(@PathVariable Integer id) {
        try {
            Admin admin = adminService.read(id);
            if (admin != null) {
                return ResponseEntity.ok(admin);
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ErrorResponse("Admin not found"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ErrorResponse("Error retrieving admin: " + e.getMessage()));
        }
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody Admin admin) {
        try {
            Admin updated = adminService.update(admin);
            if (updated != null) {
                return ResponseEntity.ok(updated);
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ErrorResponse("Admin not found"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ErrorResponse("Error updating admin: " + e.getMessage()));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        try {
            boolean deleted = adminService.delete(id);
            if (deleted) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ErrorResponse("Admin not found"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ErrorResponse("Error deleting admin: " + e.getMessage()));
        }
    }

    @GetMapping
    public ResponseEntity<List<Admin>> getAll() {
        List<Admin> admins = adminService.getAll();
        return ResponseEntity.ok(admins);
    }

    // Admin Search Operations
    @GetMapping("/firstname/{firstName}")
    public ResponseEntity<List<Admin>> findByFirstName(@PathVariable String firstName) {
        List<Admin> admins = adminService.findByFirstName(firstName);
        return ResponseEntity.ok(admins);
    }

    @GetMapping("/lastname/{lastName}")
    public ResponseEntity<List<Admin>> findByLastName(@PathVariable String lastName) {
        List<Admin> admins = adminService.findByLastName(lastName);
        return ResponseEntity.ok(admins);
    }

    @GetMapping("/level/{adminLevel}")
    public ResponseEntity<List<Admin>> findByAdminLevel(@PathVariable String adminLevel) {
        List<Admin> admins = adminService.findByAdminLevel(adminLevel);
        return ResponseEntity.ok(admins);
    }

    @GetMapping("/department/{department}")
    public ResponseEntity<List<Admin>> findByDepartment(@PathVariable String department) {
        List<Admin> admins = adminService.findByDepartment(department);
        return ResponseEntity.ok(admins);
    }

    @GetMapping("/username/{userName}")
    public ResponseEntity<?> findByUserName(@PathVariable String userName) {
        try {
            Admin admin = adminService.findByUserName(userName);
            if (admin != null) {
                return ResponseEntity.ok(admin);
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ErrorResponse("Admin not found"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ErrorResponse("Error retrieving admin: " + e.getMessage()));
        }
    }

    // Customer Management Operations
    @GetMapping("/customers")
    public ResponseEntity<List<Customer>> getAllCustomers() {
        List<Customer> customers = adminService.getAllCustomers();
        return ResponseEntity.ok(customers);
    }

    @GetMapping("/customers/{customerId}")
    public ResponseEntity<?> getCustomerById(@PathVariable Integer customerId) {
        try {
            Customer customer = adminService.getCustomerById(customerId);
            if (customer != null) {
                return ResponseEntity.ok(customer);
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ErrorResponse("Customer not found"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ErrorResponse("Error retrieving customer: " + e.getMessage()));
        }
    }

    @PutMapping("/customers")
    public ResponseEntity<?> updateCustomer(@RequestBody Customer customer) {
        try {
            Customer updated = adminService.updateCustomer(customer);
            if (updated != null) {
                return ResponseEntity.ok(updated);
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ErrorResponse("Customer not found"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ErrorResponse("Error updating customer: " + e.getMessage()));
        }
    }

    @DeleteMapping("/customers/{customerId}")
    public ResponseEntity<?> deleteCustomer(@PathVariable Integer customerId) {
        try {
            boolean deleted = adminService.deleteCustomer(customerId);
            if (deleted) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ErrorResponse("Customer not found"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ErrorResponse("Error deleting customer: " + e.getMessage()));
        }
    }

    @GetMapping("/customers/search/{name}")
    public ResponseEntity<List<Customer>> searchCustomersByName(@PathVariable String name) {
        List<Customer> customers = adminService.searchCustomersByName(name);
        return ResponseEntity.ok(customers);
    }

    @PostMapping("/customers/{customerId}/activate")
    public ResponseEntity<?> activateCustomer(@PathVariable Integer customerId) {
        try {
            Customer customer = adminService.activateCustomer(customerId);
            if (customer != null) {
                return ResponseEntity.ok(customer);
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ErrorResponse("Customer not found"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ErrorResponse("Error activating customer: " + e.getMessage()));
        }
    }

    @PostMapping("/customers/{customerId}/deactivate")
    public ResponseEntity<?> deactivateCustomer(@PathVariable Integer customerId) {
        try {
            Customer customer = adminService.deactivateCustomer(customerId);
            if (customer != null) {
                return ResponseEntity.ok(customer);
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ErrorResponse("Customer not found"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ErrorResponse("Error deactivating customer: " + e.getMessage()));
        }
    }
}