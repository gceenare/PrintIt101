/* PositionController.java
   REST Controller for Position
   Author: Siyabulela Mgijima (230680305)
   Date: 31 August 2025
*/

package za.ac.cput.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.domain.Position;
import za.ac.cput.service.PositionService;

import java.util.List;

@RestController
@RequestMapping("/position")
public class PositionController {

    private final PositionService service;

    @Autowired
    public PositionController(PositionService service) {
        this.service = service;
    }

    @PostMapping("/create")
    public Position create(@RequestBody Position position) {
        return service.create(position);
    }

    @GetMapping("/read/{id}")
    public ResponseEntity<Position> read(@PathVariable("id") Integer id) {
        Position position = service.read(id);
        if (position == null) {
            return ResponseEntity.notFound().build(); // 404
        }
        return ResponseEntity.ok(position); // 200
    }

    @PutMapping("/update")
    public Position update(@RequestBody Position position) {
        return service.update(position);
    }

    @DeleteMapping("/delete/{id}")
    public boolean delete(@PathVariable Integer id) {
        return service.delete(id);
    }

    @GetMapping("/findByX/{x}")
    public List<Position> findByX(@PathVariable double x) {
        return service.findByX(x);
    }

    @GetMapping("/findByY/{y}")
    public List<Position> findByY(@PathVariable double y) {
        return service.findByY(y);
    }

    @GetMapping("/findByZ/{z}")
    public List<Position> findByZ(@PathVariable double z) {
        return service.findByZ(z);
    }

    @GetMapping("/getAll")
    public List<Position> getAll() {
        return service.getAll();
    }
}
