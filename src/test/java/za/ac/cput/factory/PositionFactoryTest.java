package za.ac.cput.factory;

import org.junit.jupiter.api.Test;
import za.ac.cput.domain.Position;

import static org.junit.jupiter.api.Assertions.*;

class PositionFactoryTest {

    @Test
    public void testCreateValidPosition() {
        Position position = PositionFactory.createPosition(1.0, 2.5);
        assertNotNull(position);
        System.out.println("Valid Position:\n" + position);
    }

    @Test
    public void testInvalidPositionNegativeValues() {
        Position position = PositionFactory.createPosition(-9.0, -21.0);
        assertNull(position);
    }

    @Test
    public void testInvalidPositionZeroValues() {
        Position position = PositionFactory.createPosition(0.0, 0.0);
        assertNull(position);
    }
}
