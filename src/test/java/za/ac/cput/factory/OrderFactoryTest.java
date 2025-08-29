/*
OrderFactoryTest.java
Order Factory Test class
Author: L Mbangata (222558156)
Date: 11 May 2025
*/

package za.ac.cput.factory;

import org.junit.jupiter.api.Test;
import za.ac.cput.domain.Order;
import za.ac.cput.domain.OrderItem;
import za.ac.cput.domain.OrderStatus;

import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class OrderFactoryTest {

    // ------------------ Valid Order Tests ------------------
    @Test
    public void testCreateValidOrderWithMultipleItems() {
        // Create order items first
        OrderItem item1 = OrderItemFactory.createOrderItem(1, 2, 25.99);
        OrderItem item2 = OrderItemFactory.createOrderItem(2, 1, 15.50);
        List<OrderItem> orderItems = Arrays.asList(item1, item2);

        Order order = OrderFactory.createOrder(
                123, 456, 789, orderItems, OrderStatus.PENDING
        );
        assertNotNull(order);
        assertEquals(2, order.getOrderItems().size());
        System.out.println("Valid Order with Multiple Items:\n" + order);
    }

    @Test
    public void testCreateValidOrderWithSingleItem() {
        OrderItem item = OrderItemFactory.createOrderItem(1, 3, 19.99);

        Order order = OrderFactory.createOrderWithSingleItem(
                100, 200, 300, item, OrderStatus.IN_PROGRESS
        );
        assertNotNull(order);
        assertEquals(1, order.getOrderItems().size());
        System.out.println("Valid Order with Single Item:\n" + order);
    }

    @Test
    public void testCreateValidOrderWithCompletedStatus() {
        OrderItem item = OrderItemFactory.createOrderItem(5, 1, 99.99);
        List<OrderItem> orderItems = Arrays.asList(item);

        Order order = OrderFactory.createOrder(
                500, 600, 700, orderItems, OrderStatus.COMPLETED
        );
        assertNotNull(order);
        assertEquals(OrderStatus.COMPLETED, order.getOrderStatus());
        System.out.println("Valid Order with Completed Status:\n" + order);
    }

    @Test
    public void testCreateValidOrderWithCancelledStatus() {
        OrderItem item = OrderItemFactory.createOrderItem(3, 2, 49.99);
        List<OrderItem> orderItems = Arrays.asList(item);

        Order order = OrderFactory.createOrder(
                800, 900, 1000, orderItems, OrderStatus.CANCELLED
        );
        assertNotNull(order);
        assertEquals(OrderStatus.CANCELLED, order.getOrderStatus());
        System.out.println("Valid Order with Cancelled Status:\n" + order);
    }

    // ------------------ Invalid Order Tests ------------------
    @Test
    public void testCreateOrderWithInvalidUserId() {
        OrderItem item = OrderItemFactory.createOrderItem(1, 2, 25.99);
        List<OrderItem> orderItems = Arrays.asList(item);

        Order order = OrderFactory.createOrder(
                0, 456, 789, orderItems, OrderStatus.PENDING
        );
        assertNull(order);
    }

    @Test
    public void testCreateOrderWithInvalidPaymentId() {
        OrderItem item = OrderItemFactory.createOrderItem(1, 2, 25.99);
        List<OrderItem> orderItems = Arrays.asList(item);

        Order order = OrderFactory.createOrder(
                123, -1, 789, orderItems, OrderStatus.PENDING
        );
        assertNull(order);
    }

    @Test
    public void testCreateOrderWithInvalidAddressId() {
        OrderItem item = OrderItemFactory.createOrderItem(1, 2, 25.99);
        List<OrderItem> orderItems = Arrays.asList(item);

        Order order = OrderFactory.createOrder(
                123, 456, 0, orderItems, OrderStatus.PENDING
        );
        assertNull(order);
    }

    @Test
    public void testCreateOrderWithNullOrderItems() {
        Order order = OrderFactory.createOrder(
                123, 456, 789, null, OrderStatus.PENDING
        );
        assertNull(order);
    }

    @Test
    public void testCreateOrderWithEmptyOrderItems() {
        List<OrderItem> emptyList = new ArrayList<>();

        Order order = OrderFactory.createOrder(
                123, 456, 789, emptyList, OrderStatus.PENDING
        );
        assertNull(order);
    }

    @Test
    public void testCreateOrderWithNullOrderStatus() {
        OrderItem item = OrderItemFactory.createOrderItem(1, 2, 25.99);
        List<OrderItem> orderItems = Arrays.asList(item);

        Order order = OrderFactory.createOrder(
                123, 456, 789, orderItems, null
        );
        assertNull(order);
    }

    @Test
    public void testCreateSingleItemOrderWithNullItem() {
        Order order = OrderFactory.createOrderWithSingleItem(
                123, 456, 789, null, OrderStatus.PENDING
        );
        assertNull(order);
    }

    @Test
    public void testCreateCompletelyInvalidOrder() {
        Order order = OrderFactory.createOrder(
                0, -1, 0, null, null
        );
        assertNull(order);
    }
}