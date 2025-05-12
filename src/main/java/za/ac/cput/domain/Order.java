package za.ac.cput.domain;

/*
Order.java
Order POJO class
Author: L Mbangata (222558156)
Date: 11 May 2025
 */

public class Order {

    protected String orderId;
    protected int userId;
    protected int paymentId;
    protected int addressId;
    protected OrderItem orderItem;
    protected OrderStatus orderStatus;

    private Order() {
    }
    private Order(Builder builder) {
        this.orderId = builder.orderId;
        this.userId = builder.userId;
        this.paymentId = builder.paymentId;
        this.addressId = builder.addressId;
        this.orderItem = builder.orderItem;
        this.orderStatus = builder.orderStatus;

    }

    public String getOrderId() {
        return orderId;
    }

    public int getUserId() {
        return userId;
    }
    public int getPaymentId() {
        return paymentId;
    }
    public int getAddressId() {
        return addressId;
    }
    public OrderItem getOrderItem() {
        return orderItem;
    }
    public OrderStatus getOrderStatus(){
        return orderStatus;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId='" + orderId + '\'' +
                ", userId=" + userId +
                ", paymentId=" + paymentId +
                ", addressId=" + addressId +
                ", orderItem=" + orderItem +
                ", orderStatus=" + orderStatus +
                '}';
    }
    public static class Builder {
        protected String orderId;
        protected int userId;
        protected int paymentId;
        protected int addressId;
        protected OrderItem orderItem;
        protected OrderStatus orderStatus;

        public Builder setOrderId(String orderId) {
            this.orderId = orderId;
            return this;
        }
        public Builder setUserId(int userId) {
            this.userId = userId;
            return this;
        }
        public Builder setPaymentId(int paymentId) {
            this.paymentId = paymentId;
            return this;
        }
        public Builder setAddressId(int addressId) {
            this.addressId = addressId;
            return this;
        }
        public Builder setOrderItem(OrderItem orderItem) {
            this.orderItem = orderItem;
            return this;
        }
        public Builder setOrderStatus(OrderStatus orderStatus){
            this.orderStatus = orderStatus;
            return this;
        }
        public Builder copy(Order order) {
            this.orderId = order.orderId;
            this.userId = order.userId;
            this.paymentId = order.paymentId;
            this.addressId = order.addressId;
            this.orderItem = order.orderItem;
            this.orderStatus = order.orderStatus;
            return this;
        }

        public Order build(){
            return new Order(this);
        }

    }
}
