/* ScaleController.java
   Scale Controller class
   Author: Siyabulela Mgijima (230680305)
   Date: 31 August 2025
*/

package za.ac.cput.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.domain.Scale;
import za.ac.cput.service.ScaleService;

import java.util.List;

@RestController
@RequestMapping("/scale")
public class ScaleController {

    private final ScaleService service;

    @Autowired
    public ScaleController(ScaleService service) {
        this.service = service;
    }

    @PostMapping("/create")
    public Scale create(@RequestBody Scale scale) {
        return service.create(scale);
    }

    @GetMapping("/read/{id}")
    public Scale read(@PathVariable Integer id) {
        return service.read(id);
    }

    @PutMapping("/update")
    public Scale update(@RequestBody Scale scale) {
        return service.update(scale);
    }

    @DeleteMapping("/delete/{id}")
    public boolean delete(@PathVariable Integer id) {
        return service.delete(id);
    }

    @GetMapping("/findByX/{x}")
    public List<Scale> findByX(@PathVariable double x) {
        return service.findByX(x);
    }

    @GetMapping("/findByY/{y}")
    public List<Scale> findByY(@PathVariable double y) {
        return service.findByY(y);
    }

    @GetMapping("/findByZ/{z}")
    public List<Scale> findByZ(@PathVariable double z) {
        return service.findByZ(z);
    }

    @GetMapping("/getAll")
    public List<Scale> getAll() {
        return service.getAll();
    }
}
