/* ScaleController.java
   Scale Controller class
   Author: Siyabulela Mgijima (230680305)
   Date: 31 August 2025
*/

package za.ac.cput.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Scale> read(@PathVariable("id") Integer id) {
        Scale scale = service.read(id);
        if (scale == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(scale);
    }

    @PutMapping("/update")
    public Scale update(@RequestBody Scale scale) {
        return service.update(scale);
    }

    @DeleteMapping("/delete/{id}")
    public boolean delete(@PathVariable Integer id) {
        return service.delete(id);
    }

    @GetMapping("/findByValue/{value}")
    public List<Scale> findByValue(@PathVariable double value) {
        return service.findByValue(value);
    }

    @GetMapping("/getAll")
    public List<Scale> getAll() {
        return service.getAll();
    }
}