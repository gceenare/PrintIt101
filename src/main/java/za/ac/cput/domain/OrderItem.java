package za.ac.cput.domain;

/* OrderItem.java

     OrderItem POJO class

     Author: G Mbabe (222836040)

     Date: 11 May 2025 */

public class OrderItem {

    protected int orderItemId;

    protected int productId;
    protected int quantity;
    protected double pricePerUnit;


    private OrderItem() {
    }

    private OrderItem(Builder builder) {
        this.orderItemId = builder.orderItemId;
        this.productId = builder.productId;
        this.quantity = builder.quantity;
        this.pricePerUnit = builder.pricePerUnit;
    }

    public int getOrderItemId() {
        return orderItemId;
    }
    public int getProductId() {
        return productId;
    }

    public int getQuantity() {
        return quantity;
    }
    public double getPricePerUnit() {
        return pricePerUnit;
    }
    @Override
    public String toString() {
        return "OrderItem{" +
                "orderItemId=" + orderItemId +
                ", productId=" + productId +
                ", quantity=" + quantity +
                ", pricePerUnit=" + pricePerUnit +
                '}';
    }
    public static class Builder {
        private int orderItemId;
        private int productId;
        private int quantity;
        private double pricePerUnit;

        public Builder setOrderItemId(int orderItemId) {
            this.orderItemId = orderItemId;
            return this;
        }

        public Builder setProductId(int productId) {
            this.productId = productId;
            return this;
        }

        public Builder setQuantity(int quantity) {
            this.quantity = quantity;
            return this;
        }

        public Builder setPricePerUnit(double pricePerUnit) {
            this.pricePerUnit = pricePerUnit;
            return this;
        }
        public Builder copy(OrderItem orderItem) {
            this.orderItemId = orderItem.orderItemId;
            this.productId = orderItem.productId;
            this.quantity = orderItem.quantity;
            this.pricePerUnit = orderItem.pricePerUnit;
            return this;
        }

        public OrderItem build() {
            return new OrderItem(this);
        }
    }

}