package za.ac.cput.factory;

import org.junit.jupiter.api.Test;
import za.ac.cput.domain.Rotation;

import static org.junit.jupiter.api.Assertions.*;

class RotationFactoryTest {

    @Test
    public void testCreateValidRotation() {
        Rotation rotation = RotationFactory.createRotation(45.0);
        assertNotNull(rotation);
        System.out.println("Valid Rotation:\n" + rotation);
    }

    @Test
    public void testInvalidRotation() {
        Rotation rotation = RotationFactory.createRotation(460.0);
        assertNull(rotation);
    }
}
