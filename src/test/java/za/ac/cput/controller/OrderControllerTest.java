package za.ac.cput.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import za.ac.cput.domain.Order;
import za.ac.cput.domain.OrderStatus;
import za.ac.cput.service.OrderService;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OrderControllerTest {

    @Mock
    private OrderService service;

    @InjectMocks
    private OrderController controller;

    private Order testOrder;

    @BeforeEach
    void setUp() {
        testOrder = new Order.Builder()
                .setOrderId(1)
                .setUserId(100)
                .setPaymentId(200)
                .setAddressId(300)
                .setOrderStatus(OrderStatus.PENDING)
                .build();
    }

    @Test
    void create() {
        when(service.create(testOrder)).thenReturn(testOrder);

        Order result = controller.create(testOrder);

        assertNotNull(result);
        assertEquals(testOrder, result);
        verify(service, times(1)).create(testOrder);
    }

    @Test
    void read() {
        Integer orderId = 1;
        when(service.read(orderId)).thenReturn(testOrder);

        Order result = controller.read(orderId);

        assertNotNull(result);
        assertEquals(testOrder, result);
        verify(service, times(1)).read(orderId);
    }

    @Test
    void update() {
        when(service.update(testOrder)).thenReturn(testOrder);

        Order result = controller.update(testOrder);

        assertNotNull(result);
        assertEquals(testOrder, result);
        verify(service, times(1)).update(testOrder);
    }

    @Test
    void delete() {
        Integer orderId = 1;
        when(service.delete(orderId)).thenReturn(true);

        boolean result = controller.delete(orderId);

        assertTrue(result);
        verify(service, times(1)).delete(orderId);
    }

    @Test
    void getAll() {
        Order secondOrder = new Order.Builder()
                .setOrderId(2)
                .setUserId(101)
                .setPaymentId(201)
                .setAddressId(301)
                .setOrderStatus(OrderStatus.COMPLETED)
                .build();

        List<Order> orders = Arrays.asList(testOrder, secondOrder);
        when(service.getAll()).thenReturn(orders);

        List<Order> result = controller.getAll();

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(testOrder, result.get(0));
        assertEquals(secondOrder, result.get(1));
        verify(service, times(1)).getAll();
    }

    @Test
    void findByUserId() {
        int userId = 100;
        List<Order> orders = Arrays.asList(testOrder);
        when(service.findByUserId(userId)).thenReturn(orders);

        List<Order> result = controller.findByUserId(userId);

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(testOrder, result.get(0));
        verify(service, times(1)).findByUserId(userId);
    }

    @Test
    void findByPaymentId() {
        int paymentId = 200;
        when(service.findByPaymentId(paymentId)).thenReturn(testOrder);

        Order result = controller.findByPaymentId(paymentId);

        assertNotNull(result);
        assertEquals(testOrder, result);
        verify(service, times(1)).findByPaymentId(paymentId);
    }

    @Test
    void findByAddressId() {
        int addressId = 300;
        List<Order> orders = Arrays.asList(testOrder);
        when(service.findByAddressId(addressId)).thenReturn(orders);

        List<Order> result = controller.findByAddressId(addressId);

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(testOrder, result.get(0));
        verify(service, times(1)).findByAddressId(addressId);
    }

    @Test
    void findByOrderStatus() {
        OrderStatus status = OrderStatus.PENDING;
        List<Order> orders = Arrays.asList(testOrder);
        when(service.findByOrderStatus(status)).thenReturn(orders);

        List<Order> result = controller.findByOrderStatus(status);

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(testOrder, result.get(0));
        verify(service, times(1)).findByOrderStatus(status);
    }

    @Test
    void findByUserIdAndOrderStatus() {
        int userId = 100;
        OrderStatus status = OrderStatus.PENDING;
        List<Order> orders = Arrays.asList(testOrder);
        when(service.findByUserIdAndOrderStatus(userId, status)).thenReturn(orders);

        List<Order> result = controller.findByUserIdAndOrderStatus(userId, status);

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(testOrder, result.get(0));
        verify(service, times(1)).findByUserIdAndOrderStatus(userId, status);
    }

    @Test
    void findByUserIdOrderByOrderId() {
        int userId = 100;
        List<Order> orders = Arrays.asList(testOrder);
        when(service.findByUserIdOrderByOrderId(userId)).thenReturn(orders);

        List<Order> result = controller.findByUserIdOrderByOrderId(userId);

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(testOrder, result.get(0));
        verify(service, times(1)).findByUserIdOrderByOrderId(userId);
    }

    @Test
    void findByOrderStatusOrderByOrderId() {
        OrderStatus status = OrderStatus.PENDING;
        List<Order> orders = Arrays.asList(testOrder);
        when(service.findByOrderStatusOrderByOrderId(status)).thenReturn(orders);

        List<Order> result = controller.findByOrderStatusOrderByOrderId(status);

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(testOrder, result.get(0));
        verify(service, times(1)).findByOrderStatusOrderByOrderId(status);
    }

    @Test
    void findByUserIdIn() {
        List<Integer> userIds = Arrays.asList(100, 101, 102);
        List<Order> orders = Arrays.asList(testOrder);
        when(service.findByUserIdIn(userIds)).thenReturn(orders);

        List<Order> result = controller.findByUserIdIn(userIds);

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(testOrder, result.get(0));
        verify(service, times(1)).findByUserIdIn(userIds);
    }

    @Test
    void findByOrderStatusIn() {
        List<OrderStatus> statuses = Arrays.asList(OrderStatus.PENDING, OrderStatus.COMPLETED);
        List<Order> orders = Arrays.asList(testOrder);
        when(service.findByOrderStatusIn(statuses)).thenReturn(orders);

        List<Order> result = controller.findByOrderStatusIn(statuses);

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(testOrder, result.get(0));
        verify(service, times(1)).findByOrderStatusIn(statuses);
    }

    @Test
    void findByOrderItemsProductId() {
        int productId = 101;
        List<Order> orders = Arrays.asList(testOrder);
        when(service.findByOrderItems_ProductId(productId)).thenReturn(orders);

        List<Order> result = controller.findByOrderItemsProductId(productId);

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(testOrder, result.get(0));
        verify(service, times(1)).findByOrderItems_ProductId(productId);
    }

    @Test
    void findByOrderItemsOrderItemId() {
        int orderItemId = 1;
        List<Order> orders = Arrays.asList(testOrder);
        when(service.findByOrderItems_OrderItemId(orderItemId)).thenReturn(orders);

        List<Order> result = controller.findByOrderItemsOrderItemId(orderItemId);

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(testOrder, result.get(0));
        verify(service, times(1)).findByOrderItems_OrderItemId(orderItemId);
    }

    @Test
    void findByOrderItemsQuantity() {
        int quantity = 5;
        List<Order> orders = Arrays.asList(testOrder);
        when(service.findByOrderItems_Quantity(quantity)).thenReturn(orders);

        List<Order> result = controller.findByOrderItemsQuantity(quantity);

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(testOrder, result.get(0));
        verify(service, times(1)).findByOrderItems_Quantity(quantity);
    }

    @Test
    void findByOrderItemsPricePerUnitGreaterThan() {
        double pricePerUnit = 20.0;
        List<Order> orders = Arrays.asList(testOrder);
        when(service.findByOrderItems_PricePerUnitGreaterThan(pricePerUnit)).thenReturn(orders);

        List<Order> result = controller.findByOrderItemsPricePerUnitGreaterThan(pricePerUnit);

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(testOrder, result.get(0));
        verify(service, times(1)).findByOrderItems_PricePerUnitGreaterThan(pricePerUnit);
    }

    @Test
    void findByOrderItemsPricePerUnitLessThan() {
        double pricePerUnit = 30.0;
        List<Order> orders = Arrays.asList(testOrder);
        when(service.findByOrderItems_PricePerUnitLessThan(pricePerUnit)).thenReturn(orders);

        List<Order> result = controller.findByOrderItemsPricePerUnitLessThan(pricePerUnit);

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(testOrder, result.get(0));
        verify(service, times(1)).findByOrderItems_PricePerUnitLessThan(pricePerUnit);
    }

    @Test
    void findByOrderItemsPricePerUnitBetween() {
        double minPrice = 20.0;
        double maxPrice = 30.0;
        List<Order> orders = Arrays.asList(testOrder);
        when(service.findByOrderItems_PricePerUnitBetween(minPrice, maxPrice)).thenReturn(orders);

        List<Order> result = controller.findByOrderItemsPricePerUnitBetween(minPrice, maxPrice);

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(testOrder, result.get(0));
        verify(service, times(1)).findByOrderItems_PricePerUnitBetween(minPrice, maxPrice);
    }
}