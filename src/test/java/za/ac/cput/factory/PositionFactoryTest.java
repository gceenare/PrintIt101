/* PositionFactory.java
   Position Factory Test class
   Author: Siyabulela Mgijima (230680305)
   Date: 18 May 2025 */
package za.ac.cput.factory;

import org.junit.jupiter.api.Test;
import za.ac.cput.domain.Position;

import static org.junit.jupiter.api.Assertions.*;

class PositionFactoryTest {

    @Test
    public void testCreateValidPosition() {
        Position position = PositionFactory.createPosition(1.0, 2.5, -3.3);
        assertNotNull(position);
        System.out.println("Valid Position:\n" + position);
    }

    @Test
    public void testCreatePositionWithZeros() {
        Position position = PositionFactory.createPosition(0.0, 0.0, 0.0);
        assertNotNull(position);
    }
}
