package za.ac.cput.domain;

public abstract class Product {
    protected int productId;
    protected int designId;
    protected int placementDataId;
    protected String productType;

    public int getProductId() { return productId; }
    public int getDesignId() { return designId; }
    public int getPlacementDataId() { return placementDataId; }
    public String getProductType() { return productType; }
}
