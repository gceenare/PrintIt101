/* ContactController.java
   REST Controller for Contact
   Author: Thabiso Mbatha (22016299)
   Date: 31 August 2025
*/

package za.ac.cput.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.domain.Contact;
import za.ac.cput.service.ContactService;

import java.util.List;

@RestController
@RequestMapping("/contact")
public class ContactController {

    private ContactService service;

    @Autowired
    public ContactController(ContactService service) {
        this.service = service;
    }

    @PostMapping("/create")
    public Contact create(@RequestBody Contact contact) {
        return service.create(contact);
    }

    @GetMapping("/read/{id}")
    public Contact read(@PathVariable Integer id) {
        return service.read(id);
    }

    @PutMapping("/update")
    public Contact update(@RequestBody Contact contact) {
        return service.update(contact);
    }

    @DeleteMapping("/delete/{id}")
    public boolean delete(@PathVariable Integer id) {
        return service.delete(id);
    }

    @GetMapping("/getAll")
    public List<Contact> getAll() {
        return service.getAll();
    }

    @GetMapping("/findByEmail/{email}")
    public Contact findByEmail(@PathVariable String email) {
        return service.findByEmail(email);
    }

    @GetMapping("/findByPhoneNumber/{phoneNumber}")
    public Contact findByPhoneNumber(@PathVariable String phoneNumber) {
        return service.findByPhoneNumber(phoneNumber);
    }

    @GetMapping("/existsByEmail/{email}")
    public boolean existsByEmail(@PathVariable String email) {
        return service.existsByEmail(email);
    }

    @GetMapping("/existsByPhoneNumber/{phoneNumber}")
    public boolean existsByPhoneNumber(@PathVariable String phoneNumber) {
        return service.existsByPhoneNumber(phoneNumber);
    }
}
