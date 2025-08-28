/* RotationServiceTest.java
   Rotation Service Test
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
import za.ac.cput.domain.Rotation;
import za.ac.cput.factory.RotationFactory;
import za.ac.cput.repository.RotationRepository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
@Transactional
@Rollback
class RotationServiceTest {

    @Autowired
    private RotationService rotationService;

    @Autowired
    private RotationRepository rotationRepository;

    private Rotation createRotation() {
        Rotation rotation = RotationFactory.createRotation(45.0, 90.0, 180.0);
        assertNotNull(rotation, "Rotation creation failed in factory");
        return rotation;
    }

    @Test
    @Order(1)
    void create() {
        Rotation rotation = createRotation();
        Rotation saved = rotationService.create(rotation);
        assertNotNull(saved);
        assertEquals(45.0, saved.getX());
        assertEquals(90.0, saved.getY());
        assertEquals(180.0, saved.getZ());
        assertTrue(saved.getRotationId() > 0);
    }

    @Test
    @Order(2)
    void read() {
        Rotation rotation = createRotation();
        Rotation saved = rotationRepository.save(rotation);
        Rotation found = rotationService.read(saved.getRotationId());
        assertNotNull(found);
        assertEquals(90.0, found.getY());
        assertEquals(saved.getRotationId(), found.getRotationId());
    }

    @Test
    @Order(3)
    void update() {
        Rotation rotation = createRotation();
        Rotation saved = rotationRepository.save(rotation);
        Rotation updatedRotation = new Rotation.Builder()
                .copy(saved)
                .setX(10.0)
                .setY(20.0)
                .setZ(30.0)
                .build();
        Rotation updated = rotationService.update(updatedRotation);
        assertNotNull(updated);
        assertEquals(10.0, updated.getX());
        assertEquals(20.0, updated.getY());
        assertEquals(30.0, updated.getZ());
        assertEquals(saved.getRotationId(), updated.getRotationId());
    }

    @Test
    @Order(4)
    void delete() {
        Rotation rotation = createRotation();
        Rotation saved = rotationRepository.save(rotation);
        boolean deleted = rotationService.delete(saved.getRotationId());
        assertTrue(deleted);
        assertNull(rotationService.read(saved.getRotationId()));
    }

    @Test
    @Order(5)
    void getAll() {
        Rotation rotation = createRotation();
        rotationRepository.save(rotation);
        List<Rotation> rotations = rotationService.getAll();
        assertFalse(rotations.isEmpty());
        assertEquals(45.0, rotations.get(0).getX());
    }

    @Test
    @Order(6)
    void findByX() {
        Rotation rotation = createRotation();
        rotationRepository.save(rotation);
        List<Rotation> found = rotationService.findByX(45.0);
        assertFalse(found.isEmpty());
        assertEquals(45.0, found.get(0).getX());
    }

    @Test
    @Order(7)
    void findByY() {
        Rotation rotation = createRotation();
        rotationRepository.save(rotation);
        List<Rotation> found = rotationService.findByY(90.0);
        assertFalse(found.isEmpty());
        assertEquals(90.0, found.get(0).getY());
    }

    @Test
    @Order(8)
    void findByZ() {
        Rotation rotation = createRotation();
        rotationRepository.save(rotation);
        List<Rotation> found = rotationService.findByZ(180.0);
        assertFalse(found.isEmpty());
        assertEquals(180.0, found.get(0).getZ());
    }
}
