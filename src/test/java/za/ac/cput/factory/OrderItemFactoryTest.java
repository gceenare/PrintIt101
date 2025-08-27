package za.ac.cput.factory;

import org.junit.jupiter.api.Test;
import za.ac.cput.domain.OrderItem;

import static org.junit.jupiter.api.Assertions.*;

/*
OrderItemFactoryTest.java
Test class for OrderItemFactory
Author: L Mbangata (222558156)
 */

public class OrderItemFactoryTest {

    @Test
    public void testCreateValidOrderItem() {
        OrderItem orderItem = OrderItemFactory.createOrderItem(1001, 2001, 2, 99.99);
        assertNotNull(orderItem);
        assertEquals(1001, orderItem.getOrderItemId());
        assertEquals(2001, orderItem.getProductId());
        assertEquals(2, orderItem.getQuantity());
        assertEquals(99.99, orderItem.getPricePerUnit());
        System.out.println("Valid OrderItem:\n" + orderItem);
    }

    @Test
    public void testCreateOrderItemWithZeroOrderItemId() {
        OrderItem orderItem = OrderItemFactory.createOrderItem(0, 2001, 2, 99.99);
        assertNull(orderItem);
    }

    @Test
    public void testCreateOrderItemWithNegativeOrderItemId() {
        OrderItem orderItem = OrderItemFactory.createOrderItem(-1, 2001, 2, 99.99);
        assertNull(orderItem);
    }

    @Test
    public void testCreateOrderItemWithZeroProductId() {
        OrderItem orderItem = OrderItemFactory.createOrderItem(1001, 0, 2, 99.99);
        assertNull(orderItem);
    }

    @Test
    public void testCreateOrderItemWithNegativeProductId() {
        OrderItem orderItem = OrderItemFactory.createOrderItem(1001, -1, 2, 99.99);
        assertNull(orderItem);
    }

    @Test
    public void testCreateOrderItemWithZeroQuantity() {
        OrderItem orderItem = OrderItemFactory.createOrderItem(1001, 2001, 0, 99.99);
        assertNull(orderItem);
    }

    @Test
    public void testCreateOrderItemWithNegativeQuantity() {
        OrderItem orderItem = OrderItemFactory.createOrderItem(1001, 2001, -1, 99.99);
        assertNull(orderItem);
    }

    @Test
    public void testCreateOrderItemWithZeroPrice() {
        OrderItem orderItem = OrderItemFactory.createOrderItem(1001, 2001, 2, 0.0);
        assertNull(orderItem);
    }

    @Test
    public void testCreateOrderItemWithNegativePrice() {
        OrderItem orderItem = OrderItemFactory.createOrderItem(1001, 2001, 2, -99.99);
        assertNull(orderItem);
    }

    @Test
    public void testCreateCompletelyInvalidOrderItem() {
        OrderItem orderItem = OrderItemFactory.createOrderItem(-1, -1, -1, -99.99);
        assertNull(orderItem);
    }

    @Test
    public void testCreateOrderItemWithMinimumValidValues() {
        OrderItem orderItem = OrderItemFactory.createOrderItem(1, 1, 1, 0.01);
        assertNotNull(orderItem);
        assertEquals(1, orderItem.getOrderItemId());
        assertEquals(1, orderItem.getProductId());
        assertEquals(1, orderItem.getQuantity());
        assertEquals(0.01, orderItem.getPricePerUnit());
        System.out.println("Minimum Valid OrderItem:\n" + orderItem);
    }

    @Test
    public void testCreateOrderItemWithLargeValues() {
        OrderItem orderItem = OrderItemFactory.createOrderItem(999999, 888888, 1000, 9999.99);
        assertNotNull(orderItem);
        assertEquals(999999, orderItem.getOrderItemId());
        assertEquals(888888, orderItem.getProductId());
        assertEquals(1000, orderItem.getQuantity());
        assertEquals(9999.99, orderItem.getPricePerUnit());
        System.out.println("Large Values OrderItem:\n" + orderItem);
    }

    @Test
    public void testCreateOrderItemCopy() {
        OrderItem originalOrderItem = OrderItemFactory.createOrderItem(1002, 2002, 3, 149.50);
        assertNotNull(originalOrderItem);

        OrderItem copiedOrderItem = OrderItemFactory.createOrderItem(originalOrderItem);
        assertNotNull(copiedOrderItem);
        assertEquals(originalOrderItem.getOrderItemId(), copiedOrderItem.getOrderItemId());
        assertEquals(originalOrderItem.getProductId(), copiedOrderItem.getProductId());
        assertEquals(originalOrderItem.getQuantity(), copiedOrderItem.getQuantity());
        assertEquals(originalOrderItem.getPricePerUnit(), copiedOrderItem.getPricePerUnit());
        System.out.println("Copied OrderItem:\n" + copiedOrderItem);
    }

    @Test
    public void testCreateCopyOfNullOrderItem() {
        OrderItem copiedOrderItem = OrderItemFactory.createOrderItem(null);
        assertNull(copiedOrderItem);
    }

    @Test
    public void testCreateOrderItemWithModifiedQuantity() {
        OrderItem originalOrderItem = OrderItemFactory.createOrderItem(1003, 2003, 2, 75.00);
        assertNotNull(originalOrderItem);

        OrderItem modifiedOrderItem = OrderItemFactory.createOrderItemWithQuantity(originalOrderItem, 5);
        assertNotNull(modifiedOrderItem);
        assertEquals(originalOrderItem.getOrderItemId(), modifiedOrderItem.getOrderItemId());
        assertEquals(originalOrderItem.getProductId(), modifiedOrderItem.getProductId());
        assertEquals(5, modifiedOrderItem.getQuantity());
        assertEquals(originalOrderItem.getPricePerUnit(), modifiedOrderItem.getPricePerUnit());
        System.out.println("Modified Quantity OrderItem:\n" + modifiedOrderItem);
    }

    @Test
    public void testCreateOrderItemWithZeroModifiedQuantity() {
        OrderItem originalOrderItem = OrderItemFactory.createOrderItem(1004, 2004, 2, 75.00);
        assertNotNull(originalOrderItem);

        OrderItem modifiedOrderItem = OrderItemFactory.createOrderItemWithQuantity(originalOrderItem, 0);
        assertNull(modifiedOrderItem);
    }

    @Test
    public void testCreateOrderItemWithNegativeModifiedQuantity() {
        OrderItem originalOrderItem = OrderItemFactory.createOrderItem(1005, 2005, 2, 75.00);
        assertNotNull(originalOrderItem);

        OrderItem modifiedOrderItem = OrderItemFactory.createOrderItemWithQuantity(originalOrderItem, -3);
        assertNull(modifiedOrderItem);
    }

    @Test
    public void testModifyQuantityOfNullOrderItem() {
        OrderItem modifiedOrderItem = OrderItemFactory.createOrderItemWithQuantity(null, 5);
        assertNull(modifiedOrderItem);
    }

    @Test
    public void testCreateOrderItemWithModifiedPrice() {
        OrderItem originalOrderItem = OrderItemFactory.createOrderItem(1006, 2006, 2, 75.00);
        assertNotNull(originalOrderItem);

        OrderItem modifiedOrderItem = OrderItemFactory.createOrderItemWithPrice(originalOrderItem, 125.50);
        assertNotNull(modifiedOrderItem);
        assertEquals(originalOrderItem.getOrderItemId(), modifiedOrderItem.getOrderItemId());
        assertEquals(originalOrderItem.getProductId(), modifiedOrderItem.getProductId());
        assertEquals(originalOrderItem.getQuantity(), modifiedOrderItem.getQuantity());
        assertEquals(125.50, modifiedOrderItem.getPricePerUnit());
        System.out.println("Modified Price OrderItem:\n" + modifiedOrderItem);
    }

    @Test
    public void testCreateOrderItemWithZeroModifiedPrice() {
        OrderItem originalOrderItem = OrderItemFactory.createOrderItem(1007, 2007, 2, 75.00);
        assertNotNull(originalOrderItem);

        OrderItem modifiedOrderItem = OrderItemFactory.createOrderItemWithPrice(originalOrderItem, 0.0);
        assertNull(modifiedOrderItem);
    }

    @Test
    public void testCreateOrderItemWithNegativeModifiedPrice() {
        OrderItem originalOrderItem = OrderItemFactory.createOrderItem(1008, 2008, 2, 75.00);
        assertNotNull(originalOrderItem);

        OrderItem modifiedOrderItem = OrderItemFactory.createOrderItemWithPrice(originalOrderItem, -50.00);
        assertNull(modifiedOrderItem);
    }

    @Test
    public void testModifyPriceOfNullOrderItem() {
        OrderItem modifiedOrderItem = OrderItemFactory.createOrderItemWithPrice(null, 125.50);
        assertNull(modifiedOrderItem);
    }

    @Test
    public void testCreateOrderItemWithModifiedQuantityAndPrice() {
        OrderItem originalOrderItem = OrderItemFactory.createOrderItem(1009, 2009, 2, 75.00);
        assertNotNull(originalOrderItem);

        OrderItem modifiedOrderItem = OrderItemFactory.createOrderItemWithQuantityAndPrice(originalOrderItem, 4, 199.99);
        assertNotNull(modifiedOrderItem);
        assertEquals(originalOrderItem.getOrderItemId(), modifiedOrderItem.getOrderItemId());
        assertEquals(originalOrderItem.getProductId(), modifiedOrderItem.getProductId());
        assertEquals(4, modifiedOrderItem.getQuantity());
        assertEquals(199.99, modifiedOrderItem.getPricePerUnit());
        System.out.println("Modified Quantity and Price OrderItem:\n" + modifiedOrderItem);
    }

    @Test
    public void testCreateOrderItemWithQuantityAndPriceFromNull() {
        OrderItem modifiedOrderItem = OrderItemFactory.createOrderItemWithQuantityAndPrice(null, 4, 199.99);
        assertNull(modifiedOrderItem);
    }

    @Test
    public void testCreateOrderItemWithInvalidQuantityAndValidPrice() {
        OrderItem originalOrderItem = OrderItemFactory.createOrderItem(1010, 2010, 2, 75.00);
        assertNotNull(originalOrderItem);

        OrderItem modifiedOrderItem = OrderItemFactory.createOrderItemWithQuantityAndPrice(originalOrderItem, 0, 199.99);
        assertNull(modifiedOrderItem);
    }

    @Test
    public void testCreateOrderItemWithValidQuantityAndInvalidPrice() {
        OrderItem originalOrderItem = OrderItemFactory.createOrderItem(1011, 2011, 2, 75.00);
        assertNotNull(originalOrderItem);

        OrderItem modifiedOrderItem = OrderItemFactory.createOrderItemWithQuantityAndPrice(originalOrderItem, 4, 0.0);
        assertNull(modifiedOrderItem);
    }

    @Test
    public void testCreateOrderItemWithBothInvalidQuantityAndPrice() {
        OrderItem originalOrderItem = OrderItemFactory.createOrderItem(1012, 2012, 2, 75.00);
        assertNotNull(originalOrderItem);

        OrderItem modifiedOrderItem = OrderItemFactory.createOrderItemWithQuantityAndPrice(originalOrderItem, -1, -50.00);
        assertNull(modifiedOrderItem);
    }

    @Test
    public void testCreateOrderItemWithDecimalPrice() {
        OrderItem orderItem = OrderItemFactory.createOrderItem(1013, 2013, 1, 123.456);
        assertNotNull(orderItem);
        assertEquals(123.456, orderItem.getPricePerUnit());
        System.out.println("Decimal Price OrderItem:\n" + orderItem);
    }

    @Test
    public void testCreateOrderItemWithVerySmallPrice() {
        OrderItem orderItem = OrderItemFactory.createOrderItem(1014, 2014, 1, 0.001);
        assertNotNull(orderItem);
        assertEquals(0.001, orderItem.getPricePerUnit());
        System.out.println("Very Small Price OrderItem:\n" + orderItem);
    }
}