/* PlacementDataFactoryTest.java
   PlacementData Factory Test class
   Author: Siyabulela Mgijima (230680305)
   Date: 18 May 2025 */
package za.ac.cput.factory;

import org.junit.jupiter.api.Test;
import za.ac.cput.domain.PlacementData;
import za.ac.cput.domain.Position;
import za.ac.cput.domain.Rotation;
import za.ac.cput.domain.Scale;

import static org.junit.jupiter.api.Assertions.*;

class PlacementDataFactoryTest {

    @Test
    public void testCreateValidPlacementData() {
        Position position = PositionFactory.createPosition(23.0, 19.0, 3.0);
        Rotation rotation = RotationFactory.createRotation(0.0, 90.0, 240.0);
        Scale scale = ScaleFactory.createScale(10.0, 5.0, 12.0);

        PlacementData placementData = PlacementDataFactory.createPlacementData(position, rotation, scale);
        assertNotNull(placementData);
        System.out.println("Valid PlacementData:\n" + placementData);
    }

    @Test
    public void testCreatePlacementDataWithNullScale() {
        Position position = PositionFactory.createPosition(76.0, 21.0, 11.0);
        Rotation rotation = RotationFactory.createRotation(59.0, 72.0, 180.0);
        Scale scale = null;

        PlacementData placementData = PlacementDataFactory.createPlacementData(position, rotation, scale);
        assertNull(placementData);
    }

    @Test
    public void testCreatePlacementDataWithAllNull() {
        PlacementData placementData = PlacementDataFactory.createPlacementData(null, null, null);
        assertNull(placementData);
    }
}
