package za.ac.cput.service;

import org.junit.jupiter.api.*;
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
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Transactional
@Rollback
class RotationServiceTest {

    @Autowired
    private RotationService rotationService;

    @Autowired
    private RotationRepository rotationRepository;

    private Rotation createRotation() {
        Rotation rotation = RotationFactory.createRotation(45.0);
        assertNotNull(rotation, "Rotation creation failed in factory");
        return rotation;
    }

    @Test
    @Order(1)
    void create() {
        Rotation rotation = createRotation();
        Rotation saved = rotationService.create(rotation);
        assertNotNull(saved);
        assertEquals(45.0, saved.getAngle());
        assertTrue(saved.getRotationId() > 0);
    }

    @Test
    @Order(2)
    void read() {
        Rotation rotation = createRotation();
        Rotation saved = rotationRepository.save(rotation);
        Rotation found = rotationService.read(saved.getRotationId());
        assertNotNull(found);
        assertEquals(saved.getRotationId(), found.getRotationId());
        assertEquals(45.0, found.getAngle());
    }

    @Test
    @Order(3)
    void update() {
        Rotation rotation = createRotation();
        Rotation saved = rotationRepository.save(rotation);
        Rotation updatedRotation = new Rotation.Builder()
                .copy(saved)
                .setAngle(90.0)
                .build();
        Rotation updated = rotationService.update(updatedRotation);
        assertNotNull(updated);
        assertEquals(90.0, updated.getAngle());
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
        rotationRepository.deleteAll(); // clear DB

        Rotation rotation = createRotation();
        Rotation saved = rotationRepository.save(rotation);

        List<Rotation> rotations = rotationService.getAll();
        assertFalse(rotations.isEmpty());

        // Find by ID instead of assuming order
        Rotation retrieved = rotations.stream()
                .filter(r -> r.getRotationId() == saved.getRotationId())
                .findFirst()
                .orElse(null);

        assertNotNull(retrieved);
        assertEquals(45.0, retrieved.getAngle());
    }


    @Test
    @Order(6)
    void findByAngle() {
        Rotation rotation = createRotation();
        rotationRepository.save(rotation);
        List<Rotation> found = rotationService.findByAngle(45.0);
        assertFalse(found.isEmpty());
        assertEquals(45.0, found.get(0).getAngle());
    }
}
