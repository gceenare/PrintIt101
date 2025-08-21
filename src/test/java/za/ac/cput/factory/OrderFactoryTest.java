package za.ac.cput.factory;


import org.junit.jupiter.api.Test;
import za.ac.cput.domain.Order;
import za.ac.cput.domain.OrderItem;
import za.ac.cput.domain.OrderStatus;

import static org.junit.jupiter.api.Assertions.*;

/*
OrderFactoryTest.java
Test class for OrderFactory
Author: L Mbangata (222558156)
 */

public class OrderFactoryTest {


    @Test
    public void testCreateValidOrder() {
        OrderItem orderItem = new OrderItem.Builder()
                .setOrderItemId(1001)
                .setProductId(2001)
                .setQuantity(2)
                .setPricePerUnit(99.99)
                .build();
        Order order = OrderFactory.createOrder("ORD001", 101, 501, 301, orderItem, OrderStatus.PENDING);
        assertNotNull(order);
        System.out.println("Valid Order:\n" + order);
    }


    @Test
    public void testCreateValidOrderWithDefaultStatus() {
        OrderItem orderItem = new OrderItem.Builder()
                .setOrderItemId(1002)
                .setProductId(2002)
                .setQuantity(1)
                .setPricePerUnit(149.50)
                .build();
        Order order = OrderFactory.createOrder("ORD002", 102, 502, 302, orderItem);
        assertNotNull(order);
        assertEquals(OrderStatus.PENDING, order.getOrderStatus());
        System.out.println("Valid Order with Default Status:\n" + order);
    }

    @Test
    public void testCreateOrderWithNullOrderId() {
        OrderItem orderItem = new OrderItem.Builder()
                .setOrderItemId(1003)
                .setProductId(2003)
                .setQuantity(1)
                .setPricePerUnit(75.00)
                .build();
        Order order = OrderFactory.createOrder(null, 101, 501, 301, orderItem, OrderStatus.PENDING);
        assertNull(order);
    }


    @Test
    public void testCreateOrderWithEmptyOrderId() {
        OrderItem orderItem = new OrderItem.Builder()
                .setOrderItemId(1004)
                .setProductId(2004)
                .setQuantity(3)
                .setPricePerUnit(25.99)
                .build();
        Order order = OrderFactory.createOrder("", 101, 501, 301, orderItem, OrderStatus.PENDING);
        assertNull(order);
    }


    @Test
    public void testCreateOrderWithWhitespaceOrderId() {
        OrderItem orderItem = new OrderItem.Builder()
                .setOrderItemId(1005)
                .setProductId(2005)
                .setQuantity(1)
                .setPricePerUnit(199.99)
                .build();
        Order order = OrderFactory.createOrder("   ", 101, 501, 301, orderItem, OrderStatus.PENDING);
        assertNull(order);
    }

    @Test
    public void testCreateOrderWithZeroUserId() {
        OrderItem orderItem = new OrderItem.Builder()
                .setOrderItemId(1006)
                .setProductId(2006)
                .setQuantity(2)
                .setPricePerUnit(49.99)
                .build();
        Order order = OrderFactory.createOrder("ORD003", 0, 501, 301, orderItem, OrderStatus.PENDING);
        assertNull(order);
    }


    @Test
    public void testCreateOrderWithNegativeUserId() {
        OrderItem orderItem = new OrderItem.Builder()
                .setOrderItemId(1007)
                .setProductId(2007)
                .setQuantity(1)
                .setPricePerUnit(89.50)
                .build();
        Order order = OrderFactory.createOrder("ORD004", -1, 501, 301, orderItem, OrderStatus.PENDING);
        assertNull(order);
    }


    @Test
    public void testCreateOrderWithZeroPaymentId() {
        OrderItem orderItem = new OrderItem.Builder()
                .setOrderItemId(1008)
                .setProductId(2008)
                .setQuantity(1)
                .setPricePerUnit(129.99)
                .build();
        Order order = OrderFactory.createOrder("ORD005", 101, 0, 301, orderItem, OrderStatus.PENDING);
        assertNull(order);
    }


    @Test
    public void testCreateOrderWithNegativePaymentId() {
        OrderItem orderItem = new OrderItem.Builder()
                .setOrderItemId(1009)
                .setProductId(2009)
                .setQuantity(4)
                .setPricePerUnit(19.99)
                .build();
        Order order = OrderFactory.createOrder("ORD006", 101, -1, 301, orderItem, OrderStatus.PENDING);
        assertNull(order);
    }


    @Test
    public void testCreateOrderWithZeroAddressId() {
        OrderItem orderItem = new OrderItem.Builder()
                .setOrderItemId(1010)
                .setProductId(2010)
                .setQuantity(1)
                .setPricePerUnit(299.99)
                .build();
        Order order = OrderFactory.createOrder("ORD007", 101, 501, 0, orderItem, OrderStatus.PENDING);
        assertNull(order);
    }


    @Test
    public void testCreateOrderWithNegativeAddressId() {
        OrderItem orderItem = new OrderItem.Builder()
                .setOrderItemId(1011)
                .setProductId(2011)
                .setQuantity(2)
                .setPricePerUnit(159.50)
                .build();
        Order order = OrderFactory.createOrder("ORD008", 101, 501, -1, orderItem, OrderStatus.PENDING);
        assertNull(order);
    }


    @Test
    public void testCreateOrderWithNullOrderItem() {
        Order order = OrderFactory.createOrder("ORD009", 101, 501, 301, null, OrderStatus.PENDING);
        assertNull(order);
    }


    @Test
    public void testCreateOrderWithNullOrderStatus() {
        OrderItem orderItem = new OrderItem.Builder()
                .setOrderItemId(1012)
                .setProductId(2012)
                .setQuantity(1)
                .setPricePerUnit(79.99)
                .build();
        Order order = OrderFactory.createOrder("ORD010", 101, 501, 301, orderItem, null);
        assertNull(order);
    }


    @Test
    public void testCreateCompletelyInvalidOrder() {
        Order order = OrderFactory.createOrder(null, 0, 0, 0, null, null);
        assertNull(order);
    }


    @Test
    public void testCreateOrderCopy() {
        OrderItem orderItem = new OrderItem.Builder()
                .setOrderItemId(1013)
                .setProductId(2013)
                .setQuantity(3)
                .setPricePerUnit(45.50)
                .build();
        Order originalOrder = OrderFactory.createOrder("ORD011", 101, 501, 301, orderItem, OrderStatus.IN_PROGRESS);
        assertNotNull(originalOrder);

        Order copiedOrder = OrderFactory.createOrder(originalOrder);
        assertNotNull(copiedOrder);
        assertEquals(originalOrder.getOrderId(), copiedOrder.getOrderId());
        assertEquals(originalOrder.getUserId(), copiedOrder.getUserId());
        assertEquals(originalOrder.getOrderStatus(), copiedOrder.getOrderStatus());
        System.out.println("Copied Order:\n" + copiedOrder);
    }


    @Test
    public void testCreateOrderWithModifiedStatus() {
        OrderItem orderItem = new OrderItem.Builder()
                .setOrderItemId(1014)
                .setProductId(2014)
                .setQuantity(1)
                .setPricePerUnit(299.99)
                .build();
        Order originalOrder = OrderFactory.createOrder("ORD012", 101, 501, 301, orderItem, OrderStatus.PENDING);
        assertNotNull(originalOrder);

        Order modifiedOrder = OrderFactory.createOrderWithStatus(originalOrder, OrderStatus.COMPLETED);
        assertNotNull(modifiedOrder);
        assertEquals(originalOrder.getOrderId(), modifiedOrder.getOrderId());
        assertEquals(OrderStatus.COMPLETED, modifiedOrder.getOrderStatus());
        System.out.println("Modified Order:\n" + modifiedOrder);
    }


    @Test
    public void testCreateCopyOfNullOrder() {
        Order copiedOrder = OrderFactory.createOrder(null);
        assertNull(copiedOrder);
    }


    @Test
    public void testCreateOrderWithStatusFromNullOrder() {
        Order modifiedOrder = OrderFactory.createOrderWithStatus(null, OrderStatus.CANCELLED);
        assertNull(modifiedOrder);
    }


    @Test
    public void testCreateOrderWithNullStatusModification() {
        OrderItem orderItem = new OrderItem.Builder()
                .setOrderItemId(1015)
                .setProductId(2015)
                .setQuantity(2)
                .setPricePerUnit(124.99)
                .build();
        Order originalOrder = OrderFactory.createOrder("ORD013", 101, 501, 301, orderItem, OrderStatus.PENDING);
        assertNotNull(originalOrder);

        Order modifiedOrder = OrderFactory.createOrderWithStatus(originalOrder, null);
        assertNull(modifiedOrder);
    }


    @Test
    public void testOrderIdTrimming() {
        OrderItem orderItem = new OrderItem.Builder()
                .setOrderItemId(1016)
                .setProductId(2016)
                .setQuantity(1)
                .setPricePerUnit(350.00)
                .build();
        Order order = OrderFactory.createOrder("  ORD014  ", 101, 501, 301, orderItem, OrderStatus.PENDING);
        assertNotNull(order);
        assertEquals("ORD014", order.getOrderId());
        System.out.println("Order with Trimmed ID:\n" + order);
    }

}
