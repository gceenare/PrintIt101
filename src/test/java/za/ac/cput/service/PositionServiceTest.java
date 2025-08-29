/* PositionServiceTest.java
   Position Service Test
   Author: Siyabulela Mgijima (230680305)
   Date: 28 May 2025 */
package za.ac.cput.service;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import za.ac.cput.domain.Position;
import za.ac.cput.factory.PositionFactory;
import za.ac.cput.repository.PositionRepository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
@Transactional
@Rollback
class PositionServiceTest {

    @Autowired
    private PositionService positionService;

    @Autowired
    private PositionRepository positionRepository;

    private Position createPosition() {
        Position position = PositionFactory.createPosition(10.5, 20.2, 30.7);
        assertNotNull(position, "Position creation failed in factory");
        return position;
    }

    @Test
    @Order(1)
    void create() {
        Position position = createPosition();
        Position saved = positionService.create(position);
        assertNotNull(saved);
        assertEquals(10.5, saved.getX());
        assertEquals(20.2, saved.getY());
        assertEquals(30.7, saved.getZ());
        assertTrue(saved.getPositionId() > 0);
    }

    @Test
    @Order(2)
    void read() {
        Position position = createPosition();
        Position saved = positionRepository.save(position);
        Position found = positionService.read(saved.getPositionId());
        assertNotNull(found);
        assertEquals(saved.getPositionId(), found.getPositionId());
        assertEquals(10.5, found.getX());
    }

    @Test
    @Order(3)
    void update() {
        Position position = createPosition();
        Position saved = positionRepository.save(position);
        Position updatedPosition = new Position.Builder()
                .copy(saved)
                .setX(99.9)
                .setY(88.8)
                .build();
        Position updated = positionService.update(updatedPosition);
        assertNotNull(updated);
        assertEquals(99.9, updated.getX());
        assertEquals(88.8, updated.getY());
        assertEquals(saved.getPositionId(), updated.getPositionId());
    }

    @Test
    @Order(4)
    void delete() {
        Position position = createPosition();
        Position saved = positionRepository.save(position);
        boolean deleted = positionService.delete(saved.getPositionId());
        assertTrue(deleted);
        assertNull(positionService.read(saved.getPositionId()));
    }

    @Test
    @Order(5)
    void getAll() {
        Position position = createPosition();
        positionRepository.save(position);
        List<Position> positions = positionService.getAll();
        assertFalse(positions.isEmpty());
        assertEquals(10.5, positions.get(0).getX());
    }

    @Test
    @Order(6)
    void findByX() {
        Position position = createPosition();
        positionRepository.save(position);
        List<Position> found = positionService.findByX(10.5);
        assertFalse(found.isEmpty());
        assertEquals(10.5, found.get(0).getX());
    }

    @Test
    @Order(7)
    void findByY() {
        Position position = createPosition();
        positionRepository.save(position);
        List<Position> found = positionService.findByY(20.2);
        assertFalse(found.isEmpty());
        assertEquals(20.2, found.get(0).getY());
    }

    @Test
    @Order(8)
    void findByZ() {
        Position position = createPosition();
        positionRepository.save(position);
        List<Position> found = positionService.findByZ(30.7);
        assertFalse(found.isEmpty());
        assertEquals(30.7, found.get(0).getZ());
    }
}
