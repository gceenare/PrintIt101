package za.ac.cput.factory;

import za.ac.cput.domain.Design;

public class DesignFactory {
    public static Design createDesign(int designId, String filePath) {
        return new Design.Builder()
                .setDesignId(designId)
                .setFilePath(filePath)
                .build();
    }
}
