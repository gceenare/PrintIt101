package za.ac.cput.factory;

import org.junit.jupiter.api.Test;
import za.ac.cput.domain.Design;

import static org.junit.jupiter.api.Assertions.*;

class DesignFactoryTest {

    @Test
    void createDesign_shouldCreateValidDesign() {
        Design design = DesignFactory.createDesign(1, "designs/sample.png");
        assertNotNull(design);
        assertEquals(1, design.getDesignId());
        assertEquals("designs/sample.png", design.getFilePath());
    }
}
