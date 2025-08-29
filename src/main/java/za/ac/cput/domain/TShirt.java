package za.ac.cput.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "tshirts")
public class TShirt extends Product {

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Column(name = "description", length = 500)
    private String description;

    @Column(name = "price", nullable = false)
    private double price;

    @Column(name = "color", nullable = false, length = 50)
    private String color;

    @Column(name = "size", nullable = false, length = 10)
    private String size;

    protected TShirt() {}

    private TShirt(Builder builder) {
        this.productId = builder.productId;
        this.designId = builder.designId;
        this.placementDataId = builder.placementDataId;
        this.productType = "TShirt";
        this.name = builder.name;
        this.description = builder.description;
        this.price = builder.price;
        this.color = builder.color;
        this.size = builder.size;
    }

    public String getName() { return name; }
    public String getDescription() { return description; }
    public double getPrice() { return price; }
    public String getColor() { return color; }
    public String getSize() { return size; }

    @Override
    public String toString() {
        return "TShirt{" +
                "productId=" + productId +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", color='" + color + '\'' +
                ", size='" + size + '\'' +
                ", designId=" + designId +
                ", placementDataId=" + placementDataId +
                '}';
    }

    public static class Builder {
        private Integer productId;
        private Integer designId;
        private Integer placementDataId;
        private String name;
        private String description;
        private double price;
        private String color;
        private String size;

        public Builder setProductId(Integer productId) {
            this.productId = productId;
            return this;
        }

        public Builder setDesignId(Integer designId) {
            this.designId = designId;
            return this;
        }

        public Builder setPlacementDataId(Integer placementDataId) {
            this.placementDataId = placementDataId;
            return this;
        }

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setDescription(String description) {
            this.description = description;
            return this;
        }

        public Builder setPrice(double price) {
            this.price = price;
            return this;
        }

        public Builder setColor(String color) {
            this.color = color;
            return this;
        }

        public Builder setSize(String size) {
            this.size = size;
            return this;
        }

        public Builder copy(TShirt tshirt) {
            this.productId = tshirt.productId;
            this.designId = tshirt.designId;
            this.placementDataId = tshirt.placementDataId;
            this.name = tshirt.name;
            this.description = tshirt.description;
            this.price = tshirt.price;
            this.color = tshirt.color;
            this.size = tshirt.size;
            return this;
        }

        public TShirt build() {
            return new TShirt(this);
        }
    }
}
