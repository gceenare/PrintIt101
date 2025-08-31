package za.ac.cput.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.domain.Employee;
import za.ac.cput.service.EmployeeService;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
@CrossOrigin(origins = "*")
public class EmployeeController {

    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping
    public ResponseEntity<Employee> create(@RequestBody Employee employee) {
        try {
            Employee created = employeeService.create(employee);
            return new ResponseEntity<>(created, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> read(@PathVariable Integer id) {
        Employee employee = employeeService.read(id);
        if (employee != null) {
            return new ResponseEntity<>(employee, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping
    public ResponseEntity<Employee> update(@RequestBody Employee employee) {
        try {
            Employee updated = employeeService.update(employee);
            return new ResponseEntity<>(updated, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        boolean deleted = employeeService.delete(id);
        if (deleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping
    public ResponseEntity<List<Employee>> getAll() {
        List<Employee> employees = employeeService.getAll();
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    @GetMapping("/firstname/{firstName}")
    public ResponseEntity<List<Employee>> findByFirstName(@PathVariable String firstName) {
        List<Employee> employees = employeeService.findByFirstName(firstName);
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    @GetMapping("/lastname/{lastName}")
    public ResponseEntity<List<Employee>> findByLastName(@PathVariable String lastName) {
        List<Employee> employees = employeeService.findByLastName(lastName);
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    @GetMapping("/position/{position}")
    public ResponseEntity<List<Employee>> findByPosition(@PathVariable String position) {
        List<Employee> employees = employeeService.findByPosition(position);
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    @GetMapping("/username/{userName}")
    public ResponseEntity<Employee> findByUserName(@PathVariable String userName) {
        Employee employee = employeeService.findByUserName(userName);
        if (employee != null) {
            return new ResponseEntity<>(employee, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/exists/{userName}")
    public ResponseEntity<Boolean> existsByUserName(@PathVariable String userName) {
        boolean exists = employeeService.existsByUserName(userName);
        return new ResponseEntity<>(exists, HttpStatus.OK);
    }
}
