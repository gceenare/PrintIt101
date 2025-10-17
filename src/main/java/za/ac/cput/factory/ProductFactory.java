package za.ac.cput.factory;

import za.ac.cput.domain.TShirt;
import za.ac.cput.domain.Product;

public class ProductFactory {

    public static Product createProduct(String productType, int productId, int designId, int placementDataId,
                                        String name, String description, double price,
                                        String color, String size) {
        switch (productType.toUpperCase()) {
            case "TSHIRT":
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

            default:
                throw new IllegalArgumentException("Unsupported product type: " + productType);
        }
    }
}
