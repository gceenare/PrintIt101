/* ScaleServiceTest.java
   Scale Service Test
   Author: Siyabulela Mgijima (230680305)
   Date: 28 August 2025
*/
package za.ac.cput.service;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import za.ac.cput.domain.Scale;
import za.ac.cput.factory.ScaleFactory;
import za.ac.cput.repository.ScaleRepository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
@Transactional
@Rollback
class ScaleServiceTest {

    @Autowired
    private ScaleService scaleService;

    @Autowired
    private ScaleRepository scaleRepository;

    private Scale createScale() {
        Scale scale = ScaleFactory.createScale(10.0, 20.0, 30.0);
        assertNotNull(scale, "Scale creation failed in factory");
        return scale;
    }

    @Test
    @Order(1)
    void create() {
        Scale scale = createScale();
        Scale saved = scaleService.create(scale);
        assertNotNull(saved);
        assertEquals(10.0, saved.getX());
        assertEquals(20.0, saved.getY());
        assertEquals(30.0, saved.getZ());
        assertTrue(saved.getScaleId() > 0);
    }

    @Test
    @Order(2)
    void read() {
        Scale scale = createScale();
        Scale saved = scaleRepository.save(scale);
        Scale found = scaleService.read(saved.getScaleId());
        assertNotNull(found);
        assertEquals(20.0, found.getY());
        assertEquals(saved.getScaleId(), found.getScaleId());
    }

    @Test
    @Order(3)
    void update() {
        Scale scale = createScale();
        Scale saved = scaleRepository.save(scale);
        Scale updatedScale = new Scale.Builder()
                .copy(saved)
                .setX(15.0)
                .setY(25.0)
                .setZ(35.0)
                .build();
        Scale updated = scaleService.update(updatedScale);
        assertNotNull(updated);
        assertEquals(15.0, updated.getX());
        assertEquals(25.0, updated.getY());
        assertEquals(35.0, updated.getZ());
        assertEquals(saved.getScaleId(), updated.getScaleId());
    }

    @Test
    @Order(4)
    void delete() {
        Scale scale = createScale();
        Scale saved = scaleRepository.save(scale);
        boolean deleted = scaleService.delete(saved.getScaleId());
        assertTrue(deleted);
        assertNull(scaleService.read(saved.getScaleId()));
    }

    @Test
    @Order(5)
    void getAll() {
        Scale scale = createScale();
        scaleRepository.save(scale);
        List<Scale> scales = scaleService.getAll();
        assertFalse(scales.isEmpty());
        assertEquals(10.0, scales.get(0).getX());
    }

    @Test
    @Order(6)
    void findByX() {
        Scale scale = createScale();
        scaleRepository.save(scale);
        List<Scale> found = scaleService.findByX(10.0);
        assertFalse(found.isEmpty());
        assertEquals(10.0, found.get(0).getX());
    }

    @Test
    @Order(7)
    void findByY() {
        Scale scale = createScale();
        scaleRepository.save(scale);
        List<Scale> found = scaleService.findByY(20.0);
        assertFalse(found.isEmpty());
        assertEquals(20.0, found.get(0).getY());
    }

    @Test
    @Order(8)
    void findByZ() {
        Scale scale = createScale();
        scaleRepository.save(scale);
        List<Scale> found = scaleService.findByZ(30.0);
        assertFalse(found.isEmpty());
        assertEquals(30.0, found.get(0).getZ());
    }
}
