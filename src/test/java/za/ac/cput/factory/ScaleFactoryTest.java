/* ScaleFactoryTest.java
   Scale Factory Test class
   Author: Siyabulela Mgijima (230680305)
   Date: 18 May 2025 */
package za.ac.cput.factory;

import org.junit.jupiter.api.Test;
import za.ac.cput.domain.Scale;

import static org.junit.jupiter.api.Assertions.*;

class ScaleFactoryTest {

    @Test
    public void testCreateValidScale() {
        Scale scale = ScaleFactory.createScale(11.0, 20.0, 3.0);
        assertNotNull(scale);
        System.out.println("Valid Scale:\n" + scale);
    }

    @Test
    public void testInvalidScaleNegativeValues() {
        Scale scale = ScaleFactory.createScale(-9.0, -21.0, -38.0);
        assertNull(scale);
    }

    @Test
    public void testInvalidScaleZero() {
        Scale scale = ScaleFactory.createScale(0.0, 0.0, 0.0);
        assertNull(scale);
    }
}
