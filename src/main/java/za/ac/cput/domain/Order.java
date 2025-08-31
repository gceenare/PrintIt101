/*
Order.java
Order POJO class
Author: L Mbangata (222558156)
Date: 11 May 2025
*/
package za.ac.cput.domain;
import jakarta.persistence.*;
import java.util.Objects;
import java.util.List;
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int orderId;
    private int userId;
    private int paymentId;
    private int addressId;
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, fetch =
            FetchType.LAZY)
    private List<OrderItem> orderItems;
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;
    protected Order() {}
    private Order(Builder builder) {
        this.orderId = builder.orderId;
        this.userId = builder.userId;
        this.paymentId = builder.paymentId;
        this.addressId = builder.addressId;
        this.orderItems = builder.orderItems;
        this.orderStatus = builder.orderStatus;
    }
    public int getOrderId() { return orderId; }
    public int getUserId() { return userId; }
    public int getPaymentId() { return paymentId; }
    public int getAddressId() { return addressId; }
    public List<OrderItem> getOrderItems() { return orderItems; }
    public OrderStatus getOrderStatus() { return orderStatus; }
    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", userId=" + userId +
                ", paymentId=" + paymentId +
                ", addressId=" + addressId +
                ", orderItems=" + orderItems +
                ", orderStatus=" + orderStatus +
                '}';
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return orderId == order.orderId;
    }
    @Override
    public int hashCode() {
        return Objects.hash(orderId);
    }
    public static class Builder {
        private int orderId;
        private int userId;
        private int paymentId;
        private int addressId;
        private List<OrderItem> orderItems;
        private OrderStatus orderStatus;
        public Builder setOrderId(int orderId) {
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
        public Builder setOrderItems(List<OrderItem> orderItems) {
            this.orderItems = orderItems;
            return this;
        }
        public Builder setOrderStatus(OrderStatus orderStatus) {
            this.orderStatus = orderStatus;
            return this;
        }
        public Builder copy(Order order) {
            this.orderId = order.orderId;
            this.userId = order.userId;
            this.paymentId = order.paymentId;
            this.addressId = order.addressId;
            this.orderItems = order.orderItems;
            this.orderStatus = order.orderStatus;
            return this;
        }
        public Order build() {
            return new Order(this);
        }
    }
}