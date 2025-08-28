/* PlacementDataServiceTest.java
   PlacementData Service Test class
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
import za.ac.cput.domain.PlacementData;
import za.ac.cput.domain.Position;
import za.ac.cput.domain.Rotation;
import za.ac.cput.domain.Scale;
import za.ac.cput.factory.PositionFactory;
import za.ac.cput.factory.RotationFactory;
import za.ac.cput.factory.ScaleFactory;
import za.ac.cput.repository.PlacementDataRepository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
@Transactional
@Rollback
class PlacementDataServiceTest {

    @Autowired
    private PlacementDataService placementDataService;

    @Autowired
    private PlacementDataRepository placementDataRepository;

    private PlacementData createPlacementData() {
        Position position = PositionFactory.createPosition(11.0, 2.0, 7.0);
        Rotation rotation = RotationFactory.createRotation(10.9, 26.0, 33.0);
        Scale scale = ScaleFactory.createScale(12.5, 21.5, 33.5);

        PlacementData placementData = new PlacementData.Builder()
                .setPosition(position)
                .setRotation(rotation)
                .setScale(scale)
                .build();

        assertNotNull(placementData, "PlacementData creation failed in factory");
        return placementData;
    }

    @Test
    @Order(1)
    void create() {
        PlacementData pd = createPlacementData();
        PlacementData saved = placementDataService.create(pd);
        assertNotNull(saved);
        assertNotNull(saved.getPlacementDataId());
        assertEquals(pd.getPosition().getX(), saved.getPosition().getX());
    }

    @Test
    @Order(2)
    void read() {
        PlacementData pd = createPlacementData();
        PlacementData saved = placementDataRepository.save(pd);
        PlacementData found = placementDataService.read(saved.getPlacementDataId());
        assertNotNull(found);
        assertEquals(saved.getPlacementDataId(), found.getPlacementDataId());
    }

    @Test
    @Order(3)
    void update() {
        PlacementData pd = createPlacementData();
        PlacementData saved = placementDataRepository.save(pd);

        PlacementData updated = new PlacementData.Builder()
                .copy(saved)
                .setPosition(PositionFactory.createPosition(5.0, 5.0, 5.0))
                .build();

        PlacementData result = placementDataService.update(updated);
        assertEquals(5.0, result.getPosition().getX());
    }

    @Test
    @Order(4)
    void delete() {
        PlacementData pd = createPlacementData();
        PlacementData saved = placementDataRepository.save(pd);
        boolean deleted = placementDataService.delete(saved.getPlacementDataId());
        assertTrue(deleted);
        assertNull(placementDataService.read(saved.getPlacementDataId()));
    }

    @Test
    @Order(5)
    void getAll() {
        PlacementData pd = createPlacementData();
        placementDataRepository.save(pd);
        List<PlacementData> all = placementDataService.getAll();
        assertFalse(all.isEmpty());
    }

    @Test
    @Order(6)
    void findByPositionId() {
        PlacementData pd = createPlacementData();
        PlacementData saved = placementDataRepository.save(pd);
        List<PlacementData> list = placementDataService.findByPositionId(saved.getPosition().getPositionId());
        assertFalse(list.isEmpty());
    }

    @Test
    @Order(7)
    void findByRotationId() {
        PlacementData pd = createPlacementData();
        PlacementData saved = placementDataRepository.save(pd);
        List<PlacementData> list = placementDataService.findByRotationId(saved.getRotation().getRotationId());
        assertFalse(list.isEmpty());
    }

    @Test
    @Order(8)
    void findByScaleId() {
        PlacementData pd = createPlacementData();
        PlacementData saved = placementDataRepository.save(pd);
        List<PlacementData> list = placementDataService.findByScaleId(saved.getScale().getScaleId());
        assertFalse(list.isEmpty());
    }
}
