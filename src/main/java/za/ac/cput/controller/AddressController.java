/* AddressController.java
   REST Controller for Address
   Author: Thabiso Mbatha (22016299)
   Date: 31 August 2025
*/

package za.ac.cput.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.domain.Address;
import za.ac.cput.service.AddressService;

import java.util.List;

@RestController
@RequestMapping("/address")
public class AddressController {

    private AddressService service;

    @Autowired
    public AddressController(AddressService service) {
        this.service = service;
    }

    @PostMapping("/create")
    public Address create(@RequestBody Address address) {
        return service.create(address);
    }

    @GetMapping("/read/{id}")
    public Address read(@PathVariable Integer id) {
        return service.read(id);
    }

    @PutMapping("/update")
    public Address update(@RequestBody Address address) {
        return service.update(address);
    }

    @DeleteMapping("/delete/{id}")
    public boolean delete(@PathVariable Integer id) {
        return service.delete(id);
    }

    @GetMapping("/getAll")
    public List<Address> getAll() {
        return service.getAll();
    }

    @GetMapping("/findByStreet/{street}")
    public List<Address> findByStreet(@PathVariable String street) {
        return service.findByStreet(street);
    }

    @GetMapping("/findByMunicipality/{municipality}")
    public List<Address> findByMunicipality(@PathVariable String municipality) {
        return service.findByMunicipality(municipality);
    }

    @GetMapping("/findByProvince/{province}")
    public List<Address> findByProvince(@PathVariable String province) {
        return service.findByProvince(province);
    }

    @GetMapping("/findByPostalCode/{postalCode}")
    public List<Address> findByPostalCode(@PathVariable String postalCode) {
        return service.findByPostalCode(postalCode);
    }
}
