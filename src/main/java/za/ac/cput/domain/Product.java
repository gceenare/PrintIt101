package za.ac.cput.domain;

import jakarta.persistence.*;

@MappedSuperclass
public abstract class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Integer productId; // Matches your Integer usage
    protected Integer designId;
    protected Integer placementDataId;
    protected String productType;

    protected Product() {} // Default constructor for JPA

    public Integer getProductId() { return productId; }
    public Integer getDesignId() { return designId; }
    public Integer getPlacementDataId() { return placementDataId; }
    public String getProductType() { return productType; }
}
