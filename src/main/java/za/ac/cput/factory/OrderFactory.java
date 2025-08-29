/*
OrderFactory.java
Order Factory class
Author: L Mbangata (222558156)
Date: 11 May 2025
*/
package za.ac.cput.factory;
import za.ac.cput.domain.Order;
import za.ac.cput.domain.OrderItem;
import za.ac.cput.domain.OrderStatus;
import za.ac.cput.util.Helper;
import java.util.List;
public class OrderFactory {
    public static Order createOrder(int userId, int paymentId, int
            addressId, List<OrderItem> orderItems, OrderStatus orderStatus) {
        if (Helper.intIsNull(userId) || Helper.intIsNull(paymentId) ||
                Helper.intIsNull(addressId) ||
                Helper.isListNullOrEmpty(orderItems) ||
                !Helper.areAllObjectsNotNull(orderStatus)) {
            return null;
        }
        return new Order.Builder()
                .setUserId(userId)
                .setPaymentId(paymentId)
                .setAddressId(addressId)
                .setOrderItems(orderItems)
                .setOrderStatus(orderStatus)
                .build();
    }

    public static Order createOrderWithSingleItem(int userId, int
            paymentId, int addressId, OrderItem orderItem, OrderStatus orderStatus) {
        if (Helper.intIsNull(userId) || Helper.intIsNull(paymentId) ||
                Helper.intIsNull(addressId) ||
                !Helper.areAllObjectsNotNull(orderItem, orderStatus)) {
            return null;
        }
        return new Order.Builder()
                .setUserId(userId)
                .setPaymentId(paymentId)
                .setAddressId(addressId)
                .setOrderItems(List.of(orderItem))
                .setOrderStatus(orderStatus)
                .build();
    }
}
