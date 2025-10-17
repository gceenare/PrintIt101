/* PlacementDataController.java
   PlacementData Controller class
   Author: Siyabulela Mgijima (230680305)
   Date: 30 August 2025
*/
package za.ac.cput.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.domain.PlacementData;
import za.ac.cput.service.PlacementDataService;

import java.util.List;

@RestController
@RequestMapping("/placement-data")
public class PlacementDataController {

    private final PlacementDataService placementDataService;

    @Autowired
    public PlacementDataController(PlacementDataService placementDataService) {
        this.placementDataService = placementDataService;
    }

    @PostMapping("/create")
    public ResponseEntity<PlacementData> create(@RequestBody PlacementData placementData) {
        PlacementData created = placementDataService.create(placementData);
        return ResponseEntity.ok(created);
    }

    @GetMapping("/read/{id}")
    public ResponseEntity<PlacementData> read(@PathVariable Integer id) {
        PlacementData placementData = placementDataService.read(id);
        return (placementData != null) ? ResponseEntity.ok(placementData) : ResponseEntity.notFound().build();
    }

    @PutMapping("/update")
    public ResponseEntity<PlacementData> update(@RequestBody PlacementData placementData) {
        PlacementData updated = placementDataService.update(placementData);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        boolean deleted = placementDataService.delete(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

    @GetMapping("/by-position/{positionId}")
    public ResponseEntity<List<PlacementData>> findByPositionId(@PathVariable int positionId) {
        return ResponseEntity.ok(placementDataService.findByPositionId(positionId));
    }

    @GetMapping("/by-rotation/{rotationId}")
    public ResponseEntity<List<PlacementData>> findByRotationId(@PathVariable int rotationId) {
        return ResponseEntity.ok(placementDataService.findByRotationId(rotationId));
    }

    @GetMapping("/by-scale/{scaleId}")
    public ResponseEntity<List<PlacementData>> findByScaleId(@PathVariable int scaleId) {
        return ResponseEntity.ok(placementDataService.findByScaleId(scaleId));
    }

    @GetMapping("/all")
    public ResponseEntity<List<PlacementData>> getAll() {
        return ResponseEntity.ok(placementDataService.getAll());
    }
}