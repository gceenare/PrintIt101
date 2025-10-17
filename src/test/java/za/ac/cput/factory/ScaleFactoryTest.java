package za.ac.cput.factory;

import org.junit.jupiter.api.Test;
import za.ac.cput.domain.Scale;

import static org.junit.jupiter.api.Assertions.*;

class ScaleFactoryTest {

    @Test
    public void testCreateValidScale() {
        Scale scale = ScaleFactory.createScale(20.0);
        assertNotNull(scale);
        System.out.println("Valid Scale:\n" + scale);
    }

    @Test
    public void testInvalidScaleNegativeValue() {
        Scale scale = ScaleFactory.createScale(-38.0);
        assertNull(scale);
    }

    @Test
    public void testInvalidScaleZero() {
        Scale scale = ScaleFactory.createScale(0.0);
        assertNull(scale);
    }
}
