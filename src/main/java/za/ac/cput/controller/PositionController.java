/* PositionController.java
   REST Controller for Position
   Author: Siyabulela Mgijima (230680305)
   Date: 14 September 2025
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

    @GetMapping("/findByXAndY/{x}/{y}")
    public List<Position> findByXAndY(@PathVariable double x, @PathVariable double y) {
        return service.findByXAndY(x, y);
    }

    @GetMapping("/getAll")
    public List<Position> getAll() {
        return service.getAll();
    }
}
