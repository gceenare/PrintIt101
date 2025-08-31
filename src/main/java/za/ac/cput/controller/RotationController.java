/* RotationController.java
   Rotation Controller class
   Author: Siyabulela Mgijima (230680305)
   Date: 31 August 2025
*/

package za.ac.cput.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.domain.Rotation;
import za.ac.cput.service.RotationService;

import java.util.List;

@RestController
@RequestMapping("/rotation")
public class RotationController {

    private final RotationService service;

    @Autowired
    public RotationController(RotationService service) {
        this.service = service;
    }

    @PostMapping("/create")
    public Rotation create(@RequestBody Rotation rotation) {
        return service.create(rotation);
    }

    @GetMapping("/read/{id}")
    public Rotation read(@PathVariable Integer id) {
        return service.read(id);
    }

    @PutMapping("/update")
    public Rotation update(@RequestBody Rotation rotation) {
        return service.update(rotation);
    }

    @DeleteMapping("/delete/{id}")
    public boolean delete(@PathVariable Integer id) {
        return service.delete(id);
    }

    @GetMapping("/findByX/{x}")
    public List<Rotation> findByX(@PathVariable double x) {
        return service.findByX(x);
    }

    @GetMapping("/findByY/{y}")
    public List<Rotation> findByY(@PathVariable double y) {
        return service.findByY(y);
    }

    @GetMapping("/findByZ/{z}")
    public List<Rotation> findByZ(@PathVariable double z) {
        return service.findByZ(z);
    }

    @GetMapping("/getAll")
    public List<Rotation> getAll() {
        return service.getAll();
    }
}
