package za.ac.cput.factory;

import za.ac.cput.domain.TShirt;

public class TShirtFactory {
    public static TShirt createTShirt(int productId, int designId, int placementDataId,
                                      String name, String description, double price,
                                      String color, String size) {
        return new TShirt.Builder()
                .setProductId(productId)
                .setDesignId(designId)
                .setPlacementDataId(placementDataId)
                .setName(name)
                .setDescription(description)
                .setPrice(price)
                .setColor(color)
                .setSize(size)
                .build();
    }
}
