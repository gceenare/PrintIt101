package za.ac.cput.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import za.ac.cput.domain.Order;
import za.ac.cput.domain.OrderItem;
import za.ac.cput.domain.OrderStatus;
import za.ac.cput.service.OrderItemService;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OrderItemControllerTest {

    @Mock
    private OrderItemService service;

    @InjectMocks
    private OrderItemController controller;

    private OrderItem testOrderItem;
    private Order testOrder;

    @BeforeEach
    void setUp() {
        // Create a test Order first
        testOrder = new Order.Builder()
                .setOrderId(1)
                .setUserId(100)
                .setPaymentId(200)
                .setAddressId(300)
                .setOrderStatus(OrderStatus.PENDING)
                .build();

        // Create test OrderItem with realistic data
        testOrderItem = new OrderItem.Builder()
                .setOrderItemId(1)
                .setProductId(101)
                .setQuantity(5)
                .setPricePerUnit(25.99)
                .setOrder(testOrder)
                .build();
    }

    @Test
    void create() {
        when(service.create(testOrderItem)).thenReturn(testOrderItem);

        OrderItem result = controller.create(testOrderItem);

        assertNotNull(result);
        assertEquals(testOrderItem, result);
        verify(service, times(1)).create(testOrderItem);
    }

    @Test
    void read() {
        Integer orderItemId = 1;
        when(service.read(orderItemId)).thenReturn(testOrderItem);

        OrderItem result = controller.read(orderItemId);

        assertNotNull(result);
        assertEquals(testOrderItem, result);
        verify(service, times(1)).read(orderItemId);
    }

    @Test
    void update() {
        when(service.update(testOrderItem)).thenReturn(testOrderItem);

        OrderItem result = controller.update(testOrderItem);

        assertNotNull(result);
        assertEquals(testOrderItem, result);
        verify(service, times(1)).update(testOrderItem);
    }

    @Test
    void delete() {
        Integer orderItemId = 1;
        when(service.delete(orderItemId)).thenReturn(true);

        boolean result = controller.delete(orderItemId);

        assertTrue(result);
        verify(service, times(1)).delete(orderItemId);
    }

    @Test
    void getAll() {
        OrderItem secondOrderItem = new OrderItem.Builder()
                .setOrderItemId(2)
                .setProductId(102)
                .setQuantity(3)
                .setPricePerUnit(15.50)
                .build();

        List<OrderItem> orderItems = Arrays.asList(testOrderItem, secondOrderItem);
        when(service.getAll()).thenReturn(orderItems);

        List<OrderItem> result = controller.getAll();

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(testOrderItem, result.get(0));
        assertEquals(secondOrderItem, result.get(1));
        verify(service, times(1)).getAll();
    }

    @Test
    void findByProductId() {
        int productId = 101;
        List<OrderItem> orderItems = Arrays.asList(testOrderItem);
        when(service.findByProductId(productId)).thenReturn(orderItems);

        List<OrderItem> result = controller.findByProductId(productId);

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(testOrderItem, result.get(0));
        verify(service, times(1)).findByProductId(productId);
    }

    @Test
    void findByQuantity() {
        int quantity = 5;
        List<OrderItem> orderItems = Arrays.asList(testOrderItem);
        when(service.findByQuantity(quantity)).thenReturn(orderItems);

        List<OrderItem> result = controller.findByQuantity(quantity);

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(testOrderItem, result.get(0));
        verify(service, times(1)).findByQuantity(quantity);
    }

    @Test
    void findByPricePerUnit() {
        double pricePerUnit = 25.99;
        List<OrderItem> orderItems = Arrays.asList(testOrderItem);
        when(service.findByPricePerUnit(pricePerUnit)).thenReturn(orderItems);

        List<OrderItem> result = controller.findByPricePerUnit(pricePerUnit);

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(testOrderItem, result.get(0));
        verify(service, times(1)).findByPricePerUnit(pricePerUnit);
    }

    @Test
    void findByPricePerUnitGreaterThan() {
        double pricePerUnit = 20.0;
        List<OrderItem> orderItems = Arrays.asList(testOrderItem);
        when(service.findByPricePerUnitGreaterThan(pricePerUnit)).thenReturn(orderItems);

        List<OrderItem> result = controller.findByPricePerUnitGreaterThan(pricePerUnit);

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(testOrderItem, result.get(0));
        verify(service, times(1)).findByPricePerUnitGreaterThan(pricePerUnit);
    }

    @Test
    void findByPricePerUnitLessThan() {
        double pricePerUnit = 30.0;
        List<OrderItem> orderItems = Arrays.asList(testOrderItem);
        when(service.findByPricePerUnitLessThan(pricePerUnit)).thenReturn(orderItems);

        List<OrderItem> result = controller.findByPricePerUnitLessThan(pricePerUnit);

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(testOrderItem, result.get(0));
        verify(service, times(1)).findByPricePerUnitLessThan(pricePerUnit);
    }

    @Test
    void findByPricePerUnitBetween() {
        double minPrice = 20.0;
        double maxPrice = 30.0;
        List<OrderItem> orderItems = Arrays.asList(testOrderItem);
        when(service.findByPricePerUnitBetween(minPrice, maxPrice)).thenReturn(orderItems);

        List<OrderItem> result = controller.findByPricePerUnitBetween(minPrice, maxPrice);

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(testOrderItem, result.get(0));
        verify(service, times(1)).findByPricePerUnitBetween(minPrice, maxPrice);
    }

    @Test
    void findByQuantityGreaterThan() {
        int quantity = 3;
        List<OrderItem> orderItems = Arrays.asList(testOrderItem);
        when(service.findByQuantityGreaterThan(quantity)).thenReturn(orderItems);

        List<OrderItem> result = controller.findByQuantityGreaterThan(quantity);

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(testOrderItem, result.get(0));
        verify(service, times(1)).findByQuantityGreaterThan(quantity);
    }

    @Test
    void findByQuantityLessThan() {
        int quantity = 10;
        List<OrderItem> orderItems = Arrays.asList(testOrderItem);
        when(service.findByQuantityLessThan(quantity)).thenReturn(orderItems);

        List<OrderItem> result = controller.findByQuantityLessThan(quantity);

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(testOrderItem, result.get(0));
        verify(service, times(1)).findByQuantityLessThan(quantity);
    }

    @Test
    void findByQuantityBetween() {
        int minQuantity = 3;
        int maxQuantity = 10;
        List<OrderItem> orderItems = Arrays.asList(testOrderItem);
        when(service.findByQuantityBetween(minQuantity, maxQuantity)).thenReturn(orderItems);

        List<OrderItem> result = controller.findByQuantityBetween(minQuantity, maxQuantity);

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(testOrderItem, result.get(0));
        verify(service, times(1)).findByQuantityBetween(minQuantity, maxQuantity);
    }

    @Test
    void findByProductIdIn() {
        List<Integer> productIds = Arrays.asList(101, 102, 103);
        List<OrderItem> orderItems = Arrays.asList(testOrderItem);
        when(service.findByProductIdIn(productIds)).thenReturn(orderItems);

        List<OrderItem> result = controller.findByProductIdIn(productIds);

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(testOrderItem, result.get(0));
        verify(service, times(1)).findByProductIdIn(productIds);
    }

    @Test
    void findByQuantityIn() {
        List<Integer> quantities = Arrays.asList(3, 5, 7);
        List<OrderItem> orderItems = Arrays.asList(testOrderItem);
        when(service.findByQuantityIn(quantities)).thenReturn(orderItems);

        List<OrderItem> result = controller.findByQuantityIn(quantities);

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(testOrderItem, result.get(0));
        verify(service, times(1)).findByQuantityIn(quantities);
    }

    @Test
    void findByProductIdAndQuantity() {
        int productId = 101;
        int quantity = 5;
        List<OrderItem> orderItems = Arrays.asList(testOrderItem);
        when(service.findByProductIdAndQuantity(productId, quantity)).thenReturn(orderItems);

        List<OrderItem> result = controller.findByProductIdAndQuantity(productId, quantity);

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(testOrderItem, result.get(0));
        verify(service, times(1)).findByProductIdAndQuantity(productId, quantity);
    }

    @Test
    void findByProductIdAndPricePerUnit() {
        int productId = 101;
        double pricePerUnit = 25.99;
        List<OrderItem> orderItems = Arrays.asList(testOrderItem);
        when(service.findByProductIdAndPricePerUnit(productId, pricePerUnit)).thenReturn(orderItems);

        List<OrderItem> result = controller.findByProductIdAndPricePerUnit(productId, pricePerUnit);

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(testOrderItem, result.get(0));
        verify(service, times(1)).findByProductIdAndPricePerUnit(productId, pricePerUnit);
    }

    @Test
    void findByQuantityAndPricePerUnit() {
        int quantity = 5;
        double pricePerUnit = 25.99;
        List<OrderItem> orderItems = Arrays.asList(testOrderItem);
        when(service.findByQuantityAndPricePerUnit(quantity, pricePerUnit)).thenReturn(orderItems);

        List<OrderItem> result = controller.findByQuantityAndPricePerUnit(quantity, pricePerUnit);

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(testOrderItem, result.get(0));
        verify(service, times(1)).findByQuantityAndPricePerUnit(quantity, pricePerUnit);
    }

    @Test
    void findByProductIdAndQuantityGreaterThan() {
        int productId = 101;
        int quantity = 3;
        List<OrderItem> orderItems = Arrays.asList(testOrderItem);
        when(service.findByProductIdAndQuantityGreaterThan(productId, quantity)).thenReturn(orderItems);

        List<OrderItem> result = controller.findByProductIdAndQuantityGreaterThan(productId, quantity);

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(testOrderItem, result.get(0));
        verify(service, times(1)).findByProductIdAndQuantityGreaterThan(productId, quantity);
    }

    @Test
    void findByProductIdAndPricePerUnitGreaterThan() {
        int productId = 101;
        double pricePerUnit = 20.0;
        List<OrderItem> orderItems = Arrays.asList(testOrderItem);
        when(service.findByProductIdAndPricePerUnitGreaterThan(productId, pricePerUnit)).thenReturn(orderItems);

        List<OrderItem> result = controller.findByProductIdAndPricePerUnitGreaterThan(productId, pricePerUnit);

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(testOrderItem, result.get(0));
        verify(service, times(1)).findByProductIdAndPricePerUnitGreaterThan(productId, pricePerUnit);
    }

    @Test
    void findByProductIdOrderByQuantity() {
        int productId = 101;
        List<OrderItem> orderItems = Arrays.asList(testOrderItem);
        when(service.findByProductIdOrderByQuantity(productId)).thenReturn(orderItems);

        List<OrderItem> result = controller.findByProductIdOrderByQuantity(productId);

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(testOrderItem, result.get(0));
        verify(service, times(1)).findByProductIdOrderByQuantity(productId);
    }

    @Test
    void findByProductIdOrderByPricePerUnit() {
        int productId = 101;
        List<OrderItem> orderItems = Arrays.asList(testOrderItem);
        when(service.findByProductIdOrderByPricePerUnit(productId)).thenReturn(orderItems);

        List<OrderItem> result = controller.findByProductIdOrderByPricePerUnit(productId);

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(testOrderItem, result.get(0));
        verify(service, times(1)).findByProductIdOrderByPricePerUnit(productId);
    }

    @Test
    void findByQuantityOrderByPricePerUnit() {
        int quantity = 5;
        List<OrderItem> orderItems = Arrays.asList(testOrderItem);
        when(service.findByQuantityOrderByPricePerUnit(quantity)).thenReturn(orderItems);

        List<OrderItem> result = controller.findByQuantityOrderByPricePerUnit(quantity);

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(testOrderItem, result.get(0));
        verify(service, times(1)).findByQuantityOrderByPricePerUnit(quantity);
    }

    @Test
    void findAllByOrderByProductId() {
        List<OrderItem> orderItems = Arrays.asList(testOrderItem);
        when(service.findAllByOrderByProductId()).thenReturn(orderItems);

        List<OrderItem> result = controller.findAllByOrderByProductId();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(testOrderItem, result.get(0));
        verify(service, times(1)).findAllByOrderByProductId();
    }

    @Test
    void findAllByOrderByQuantityDesc() {
        List<OrderItem> orderItems = Arrays.asList(testOrderItem);
        when(service.findAllByOrderByQuantityDesc()).thenReturn(orderItems);

        List<OrderItem> result = controller.findAllByOrderByQuantityDesc();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(testOrderItem, result.get(0));
        verify(service, times(1)).findAllByOrderByQuantityDesc();
    }

    @Test
    void findAllByOrderByPricePerUnitDesc() {
        List<OrderItem> orderItems = Arrays.asList(testOrderItem);
        when(service.findAllByOrderByPricePerUnitDesc()).thenReturn(orderItems);

        List<OrderItem> result = controller.findAllByOrderByPricePerUnitDesc();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(testOrderItem, result.get(0));
        verify(service, times(1)).findAllByOrderByPricePerUnitDesc();
    }

    @Test
    void findByOrderOrderId() {
        int orderId = 1;
        List<OrderItem> orderItems = Arrays.asList(testOrderItem);
        when(service.findByOrder_OrderId(orderId)).thenReturn(orderItems);

        List<OrderItem> result = controller.findByOrderOrderId(orderId);

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(testOrderItem, result.get(0));
        verify(service, times(1)).findByOrder_OrderId(orderId);
    }

    @Test
    void findByOrderUserId() {
        int userId = 100;
        List<OrderItem> orderItems = Arrays.asList(testOrderItem);
        when(service.findByOrder_UserId(userId)).thenReturn(orderItems);

        List<OrderItem> result = controller.findByOrderUserId(userId);

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(testOrderItem, result.get(0));
        verify(service, times(1)).findByOrder_UserId(userId);
    }

    @Test
    void findByOrderOrderStatus() {
        OrderStatus orderStatus = OrderStatus.PENDING;
        List<OrderItem> orderItems = Arrays.asList(testOrderItem);
        when(service.findByOrder_OrderStatus(orderStatus)).thenReturn(orderItems);

        List<OrderItem> result = controller.findByOrderOrderStatus(orderStatus);

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(testOrderItem, result.get(0));
        verify(service, times(1)).findByOrder_OrderStatus(orderStatus);
    }

    @Test
    void findByOrderPaymentId() {
        int paymentId = 200;
        List<OrderItem> orderItems = Arrays.asList(testOrderItem);
        when(service.findByOrder_PaymentId(paymentId)).thenReturn(orderItems);

        List<OrderItem> result = controller.findByOrderPaymentId(paymentId);

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(testOrderItem, result.get(0));
        verify(service, times(1)).findByOrder_PaymentId(paymentId);
    }

    @Test
    void findByOrderAddressId() {
        int addressId = 300;
        List<OrderItem> orderItems = Arrays.asList(testOrderItem);
        when(service.findByOrder_AddressId(addressId)).thenReturn(orderItems);

        List<OrderItem> result = controller.findByOrderAddressId(addressId);

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(testOrderItem, result.get(0));
        verify(service, times(1)).findByOrder_AddressId(addressId);
    }

    // Additional tests for edge cases and error handling

    @Test
    void createWithNullOrderItem() {
        when(service.create(null)).thenReturn(null);

        OrderItem result = controller.create(null);

        assertNull(result);
        verify(service, times(1)).create(null);
    }

    @Test
    void readWithNonExistentId() {
        Integer nonExistentId = 999;
        when(service.read(nonExistentId)).thenReturn(null);

        OrderItem result = controller.read(nonExistentId);

        assertNull(result);
        verify(service, times(1)).read(nonExistentId);
    }

    @Test
    void deleteWithNonExistentId() {
        Integer nonExistentId = 999;
        when(service.delete(nonExistentId)).thenReturn(false);

        boolean result = controller.delete(nonExistentId);

        assertFalse(result);
        verify(service, times(1)).delete(nonExistentId);
    }

    @Test
    void findByProductIdWithNoResults() {
        int productId = 999;
        List<OrderItem> emptyList = Arrays.asList();
        when(service.findByProductId(productId)).thenReturn(emptyList);

        List<OrderItem> result = controller.findByProductId(productId);

        assertNotNull(result);
        assertTrue(result.isEmpty());
        verify(service, times(1)).findByProductId(productId);
    }

    @Test
    void findByQuantityWithNoResults() {
        int quantity = 999;
        List<OrderItem> emptyList = Arrays.asList();
        when(service.findByQuantity(quantity)).thenReturn(emptyList);

        List<OrderItem> result = controller.findByQuantity(quantity);

        assertNotNull(result);
        assertTrue(result.isEmpty());
        verify(service, times(1)).findByQuantity(quantity);
    }

    @Test
    void findByPricePerUnitWithNoResults() {
        double pricePerUnit = 999.99;
        List<OrderItem> emptyList = Arrays.asList();
        when(service.findByPricePerUnit(pricePerUnit)).thenReturn(emptyList);

        List<OrderItem> result = controller.findByPricePerUnit(pricePerUnit);

        assertNotNull(result);
        assertTrue(result.isEmpty());
        verify(service, times(1)).findByPricePerUnit(pricePerUnit);
    }

    @Test
    void getAllWithEmptyDatabase() {
        List<OrderItem> emptyList = Arrays.asList();
        when(service.getAll()).thenReturn(emptyList);

        List<OrderItem> result = controller.getAll();

        assertNotNull(result);
        assertTrue(result.isEmpty());
        verify(service, times(1)).getAll();
    }
}