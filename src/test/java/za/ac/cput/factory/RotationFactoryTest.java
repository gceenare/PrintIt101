/* RotationFactoryTest.java
   Rotation Factory Test class
   Author: Siyabulela Mgijima (230680305)
   Date: 18 May 2025 */
package za.ac.cput.factory;

import org.junit.jupiter.api.Test;
import za.ac.cput.domain.Rotation;

import static org.junit.jupiter.api.Assertions.*;

class RotationFactoryTest {

    @Test
    public void testCreateValidRotation() {
        Rotation rotation = RotationFactory.createRotation(0.0, 45.0, 90.0);
        assertNotNull(rotation);
        System.out.println("Valid Rotation:\n" + rotation);
    }

    @Test
    public void testInvalidRotationX() {
        Rotation rotation = RotationFactory.createRotation(460.0, 0.0, 0.0);
        assertNull(rotation);
    }

    @Test
    public void testInvalidRotationAllAxes() {
        Rotation rotation = RotationFactory.createRotation(600.0, -90.0, 740.0);
        assertNull(rotation);
    }
}
