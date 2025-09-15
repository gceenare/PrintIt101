package za.ac.cput.service;

import org.junit.jupiter.api.*;
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
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Transactional
@Rollback
class ScaleServiceTest {

    @Autowired
    private ScaleService scaleService;

    @Autowired
    private ScaleRepository scaleRepository;

    private Scale createScale() {
        Scale scale = ScaleFactory.createScale(10.0);
        assertNotNull(scale, "Scale creation failed in factory");
        return scale;
    }

    @Test
    @Order(1)
    void create() {
        Scale scale = createScale();
        Scale saved = scaleService.create(scale);
        assertNotNull(saved);
        assertEquals(10.0, saved.getValue());
        assertTrue(saved.getScaleId() > 0);
    }

    @Test
    @Order(2)
    void read() {
        Scale scale = createScale();
        Scale saved = scaleRepository.save(scale);
        Scale found = scaleService.read(saved.getScaleId());
        assertNotNull(found);
        assertEquals(saved.getScaleId(), found.getScaleId());
        assertEquals(10.0, found.getValue());
    }

    @Test
    @Order(3)
    void update() {
        Scale scale = createScale();
        Scale saved = scaleRepository.save(scale);
        Scale updatedScale = new Scale.Builder()
                .copy(saved)
                .setValue(20.0)
                .build();
        Scale updated = scaleService.update(updatedScale);
        assertNotNull(updated);
        assertEquals(20.0, updated.getValue());
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
        scaleRepository.deleteAll(); // clean DB before test

        Scale scale = createScale();
        Scale saved = scaleRepository.save(scale);

        List<Scale> scales = scaleService.getAll();
        assertFalse(scales.isEmpty());

        // Find by ID instead of assuming order
        Scale retrieved = scales.stream()
                .filter(s -> s.getScaleId() == saved.getScaleId())
                .findFirst()
                .orElse(null);

        assertNotNull(retrieved);
        assertEquals(10.0, retrieved.getValue());
    }


    @Test
    @Order(6)
    void findByValue() {
        Scale scale = createScale();
        scaleRepository.save(scale);
        List<Scale> found = scaleService.findByValue(10.0);
        assertFalse(found.isEmpty());
        assertEquals(10.0, found.get(0).getValue());
    }
}
