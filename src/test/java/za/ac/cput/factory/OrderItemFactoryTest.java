/*
OrderItemFactoryTest.java
OrderItem Factory Test class
Author: L Mbangata (222558156)
Date: 11 May 2025
*/

package za.ac.cput.factory;

import org.junit.jupiter.api.Test;
import za.ac.cput.domain.Order;
import za.ac.cput.domain.OrderItem;
import za.ac.cput.domain.OrderStatus;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class OrderItemFactoryTest {

    // ------------------ Valid OrderItem Tests ------------------
    @Test
    public void testCreateValidOrderItem() {
        OrderItem orderItem = OrderItemFactory.createOrderItem(1, 2, 25.99);
        assertNotNull(orderItem);
        assertEquals(1, orderItem.getProductId());
        assertEquals(2, orderItem.getQuantity());
        assertEquals(25.99, orderItem.getPricePerUnit());
        System.out.println("Valid OrderItem:\n" + orderItem);
    }

    @Test
    public void testCreateValidOrderItemWithHighQuantity() {
        OrderItem orderItem = OrderItemFactory.createOrderItem(5, 100, 9.99);
        assertNotNull(orderItem);
        assertEquals(100, orderItem.getQuantity());
        System.out.println("Valid OrderItem with High Quantity:\n" + orderItem);
    }

    @Test
    public void testCreateValidOrderItemWithDecimalPrice() {
        OrderItem orderItem = OrderItemFactory.createOrderItem(3, 1, 199.95);
        assertNotNull(orderItem);
        assertEquals(199.95, orderItem.getPricePerUnit());
        System.out.println("Valid OrderItem with Decimal Price:\n" + orderItem);
    }

    @Test
    public void testCreateValidOrderItemWithOrder() {
        // First create an order item without order reference
        OrderItem item = OrderItemFactory.createOrderItem(1, 2, 25.99);

        // Create an order
        Order order = OrderFactory.createOrderWithSingleItem(
                123, 456, 789, item, OrderStatus.IN_PROGRESS
        );

        // Now create order item with order reference
        OrderItem orderItemWithOrder = OrderItemFactory.createOrderItemWithOrder(
                2, 3, 15.50, order
        );
        assertNotNull(orderItemWithOrder);
        assertNotNull(orderItemWithOrder.getOrder());
        assertEquals(order, orderItemWithOrder.getOrder());
        System.out.println("Valid OrderItem with Order:\n" + orderItemWithOrder);
    }

    // ------------------ Invalid OrderItem Tests ------------------
    @Test
    public void testCreateOrderItemWithInvalidProductId() {
        OrderItem orderItem = OrderItemFactory.createOrderItem(0, 2, 25.99);
        assertNull(orderItem);
    }

    @Test
    public void testCreateOrderItemWithNegativeProductId() {
        OrderItem orderItem = OrderItemFactory.createOrderItem(-1, 2, 25.99);
        assertNull(orderItem);
    }

    @Test
    public void testCreateOrderItemWithInvalidQuantity() {
        OrderItem orderItem = OrderItemFactory.createOrderItem(1, 0, 25.99);
        assertNull(orderItem);
    }

    @Test
    public void testCreateOrderItemWithNegativeQuantity() {
        OrderItem orderItem = OrderItemFactory.createOrderItem(1, -5, 25.99);
        assertNull(orderItem);
    }

    @Test
    public void testCreateOrderItemWithInvalidPrice() {
        OrderItem orderItem = OrderItemFactory.createOrderItem(1, 2, 0.0);
        assertNull(orderItem);
    }

    @Test
    public void testCreateOrderItemWithNegativePrice() {
        OrderItem orderItem = OrderItemFactory.createOrderItem(1, 2, -15.99);
        assertNull(orderItem);
    }

    @Test
    public void testCreateOrderItemWithNaNPrice() {
        OrderItem orderItem = OrderItemFactory.createOrderItem(1, 2, Double.NaN);
        assertNull(orderItem);
    }

    @Test
    public void testCreateOrderItemWithInfinitePrice() {
        OrderItem orderItem = OrderItemFactory.createOrderItem(1, 2, Double.POSITIVE_INFINITY);
        assertNull(orderItem);
    }

    @Test
    public void testCreateOrderItemWithOrderButNullOrder() {
        OrderItem orderItem = OrderItemFactory.createOrderItemWithOrder(1, 2, 25.99, null);
        assertNull(orderItem);
    }

    @Test
    public void testCreateCompletelyInvalidOrderItem() {
        OrderItem orderItem = OrderItemFactory.createOrderItem(-1, 0, -25.99);
        assertNull(orderItem);
    }

    @Test
    public void testCreateCompletelyInvalidOrderItemWithOrder() {
        OrderItem orderItem = OrderItemFactory.createOrderItemWithOrder(0, -1, Double.NaN, null);
        assertNull(orderItem);
    }
}