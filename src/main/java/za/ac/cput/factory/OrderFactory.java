package za.ac.cput.factory;

import za.ac.cput.domain.Order;
import za.ac.cput.domain.OrderItem;
import za.ac.cput.domain.OrderStatus;

/*
OrderFactory.java
Factory class for Order
Author: L Mbangata (222558156)
 */

public class OrderFactory {
    public static Order createOrder(String orderId, int userId, int paymentId, int addressId,
                                    OrderItem orderItem, OrderStatus orderStatus) {

        // Validation
        if (orderId == null || orderId.trim().isEmpty()) {
            return null;
        }

        if (userId <= 0) {
            return null;
        }

        if (paymentId <= 0) {
            return null;
        }

        if (addressId <= 0) {
            return null;
        }

        if (orderItem == null) {
            return null;
        }

        if (orderStatus == null) {
            return null;
        }

        return new Order.Builder()
                .setOrderId(orderId.trim())
                .setUserId(userId)
                .setPaymentId(paymentId)
                .setAddressId(addressId)
                .setOrderItem(orderItem)
                .setOrderStatus(orderStatus)
                .build();
    }

    // Overloaded method with default status
    public static Order createOrder(String orderId, int userId, int paymentId, int addressId,
                                    OrderItem orderItem) {
        return createOrder(orderId, userId, paymentId, addressId, orderItem, OrderStatus.PENDING);
    }

    // Method to create a copy of an existing order with modifications
    public static Order createOrder(Order existingOrder) {
        if (existingOrder == null) {
            return null;
        }

        return new Order.Builder()
                .copy(existingOrder)
                .build();
    }

    // Method to create order with modified status
    public static Order createOrderWithStatus(Order existingOrder, OrderStatus newStatus) {
        if (existingOrder == null || newStatus == null) {
            return null;
        }

        return new Order.Builder()
                .copy(existingOrder)
                .setOrderStatus(newStatus)
                .build();
    }
}
