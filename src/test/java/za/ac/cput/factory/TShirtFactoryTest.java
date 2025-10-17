package za.ac.cput.factory;

import org.junit.jupiter.api.Test;
import za.ac.cput.domain.TShirt;

import static org.junit.jupiter.api.Assertions.*;

class TShirtFactoryTest {

    @Test
    void createTShirt_shouldCreateValidTShirt() {
        TShirt tshirt = TShirtFactory.createTShirt(
                1, 101, 201,
                "Classic Tee", "Soft cotton", 199.99,
                "Black", "M");

        assertNotNull(tshirt);
        assertEquals("Classic Tee", tshirt.getName());
        assertEquals(199.99, tshirt.getPrice());
        assertEquals("Black", tshirt.getColor());
    }
}
