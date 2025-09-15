package za.ac.cput.service;

import org.junit.jupiter.api.*;
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
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Transactional
@Rollback
class PositionServiceTest {

    @Autowired
    private PositionService positionService;

    @Autowired
    private PositionRepository positionRepository;

    private Position createPosition() {
        Position position = PositionFactory.createPosition(10.5, 20.2);
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
        positionRepository.deleteAll(); // ensure DB is clean

        Position position = createPosition();
        Position saved = positionRepository.save(position);

        List<Position> positions = positionService.getAll();
        assertFalse(positions.isEmpty());

        // Find the saved entity by ID instead of assuming it's first in the list
        Position retrieved = positions.stream()
                .filter(p -> p.getPositionId() == saved.getPositionId())
                .findFirst()
                .orElse(null);

        assertNotNull(retrieved);
        assertEquals(10.5, retrieved.getX());
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
}
