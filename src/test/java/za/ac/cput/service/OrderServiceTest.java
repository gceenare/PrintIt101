package za.ac.cput.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import za.ac.cput.domain.*;
import za.ac.cput.factory.OrderFactory;
import za.ac.cput.factory.OrderItemFactory;
import za.ac.cput.repository.OrderRepository;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@Rollback
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class OrderServiceTest {

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderRepository orderRepository;

    private za.ac.cput.domain.Order order;

    private za.ac.cput.domain.Order createOrder() {
        // Create a sample OrderItem first
        OrderItem orderItem = OrderItemFactory.createOrderItem(1, 5, 29.99);
        List<OrderItem> orderItems = Arrays.asList(orderItem);

        // Create order using factory
        return OrderFactory.createOrder(
                1, // userId
                1, // paymentId
                1, // addressId
                orderItems, // orderItems
                OrderStatus.PENDING // orderStatus
        );
    }

    @BeforeEach
    void setUp() {
        order = createOrder();
        assertNotNull(order, "Order creation failed in factory");
    }

    @Test
    @Order(1)
    void create() {
        za.ac.cput.domain.Order saved = orderService.create(order);
        assertNotNull(saved);
        assertEquals(1, saved.getUserId());
        assertEquals(1, saved.getPaymentId());
        assertEquals(1, saved.getAddressId());
        assertEquals(OrderStatus.PENDING, saved.getOrderStatus());
        assertTrue(saved.getOrderId() > 0);
        System.out.println("Created Order: " + saved);
    }

    @Test
    @Order(2)
    void read() {
        za.ac.cput.domain.Order saved = orderRepository.save(order);
        za.ac.cput.domain.Order found = orderService.read(saved.getOrderId());
        assertNotNull(found);
        assertEquals(1, found.getUserId());
        assertEquals(saved.getOrderId(), found.getOrderId());
        System.out.println("Read Order: " + found);
    }

    @Test
    @Order(3)
    void update() {
        za.ac.cput.domain.Order saved = orderRepository.save(order);
        za.ac.cput.domain.Order updatedOrder = new za.ac.cput.domain.Order.Builder()
                .copy(saved)
                .setOrderStatus(OrderStatus.IN_PROGRESS)
                .setUserId(2)
                .build();
        za.ac.cput.domain.Order updated = orderService.update(updatedOrder);
        assertNotNull(updated);
        assertEquals(OrderStatus.IN_PROGRESS, updated.getOrderStatus());
        assertEquals(2, updated.getUserId());
        assertEquals(saved.getOrderId(), updated.getOrderId());
        System.out.println("Updated Order: " + updated);
    }

    @Test
    @Order(4)
    void delete() {
        za.ac.cput.domain.Order saved = orderRepository.save(order);
        boolean deleted = orderService.delete(saved.getOrderId());
        assertTrue(deleted);
        assertNull(orderService.read(saved.getOrderId()));
        System.out.println("Order deleted successfully");
    }

    @Test
    @Order(5)
    void getAll() {
        orderService.create(order);
        List<za.ac.cput.domain.Order> orders = orderService.getAll();
        assertNotNull(orders);
        assertFalse(orders.isEmpty());
        System.out.println("All Orders: " + orders);
    }

    @Test
    @Order(6)
    void findByUserId() {
        orderRepository.save(order);
        List<za.ac.cput.domain.Order> found = orderService.findByUserId(1);
        assertFalse(found.isEmpty());
        assertEquals(1, found.get(0).getUserId());
        System.out.println("Found by User ID: " + found);
    }

    @Test
    @Order(7)
    void findByPaymentId() {
        orderRepository.save(order);
        za.ac.cput.domain.Order found = orderService.findByPaymentId(1);
        assertNotNull(found);
        assertEquals(1, found.getPaymentId());
        System.out.println("Found by Payment ID: " + found);
    }

    @Test
    @Order(8)
    void findByAddressId() {
        orderRepository.save(order);
        List<za.ac.cput.domain.Order> found = orderService.findByAddressId(1);
        assertFalse(found.isEmpty());
        assertEquals(1, found.get(0).getAddressId());
        System.out.println("Found by Address ID: " + found);
    }

    @Test
    @Order(9)
    void findByOrderStatus() {
        orderRepository.save(order);
        List<za.ac.cput.domain.Order> found = orderService.findByOrderStatus(OrderStatus.PENDING);
        assertFalse(found.isEmpty());
        assertEquals(OrderStatus.PENDING, found.get(0).getOrderStatus());
        System.out.println("Found by Order Status: " + found);
    }

    @Test
    @Order(10)
    void findByUserIdAndOrderStatus() {
        orderRepository.save(order);
        List<za.ac.cput.domain.Order> found = orderService.findByUserIdAndOrderStatus(1, OrderStatus.PENDING);
        assertFalse(found.isEmpty());
        assertEquals(1, found.get(0).getUserId());
        assertEquals(OrderStatus.PENDING, found.get(0).getOrderStatus());
        System.out.println("Found by User ID and Order Status: " + found);
    }

    @Test
    @Order(11)
    void findByUserIdOrderByOrderId() {
        orderRepository.save(order);
        List<za.ac.cput.domain.Order> found = orderService.findByUserIdOrderByOrderId(1);
        assertFalse(found.isEmpty());
        assertEquals(1, found.get(0).getUserId());
        System.out.println("Found by User ID ordered by Order ID: " + found);
    }

    @Test
    @Order(12)
    void findByOrderStatusOrderByOrderId() {
        orderRepository.save(order);
        List<za.ac.cput.domain.Order> found = orderService.findByOrderStatusOrderByOrderId(OrderStatus.PENDING);
        assertFalse(found.isEmpty());
        assertEquals(OrderStatus.PENDING, found.get(0).getOrderStatus());
        System.out.println("Found by Order Status ordered by Order ID: " + found);
    }

    @Test
    @Order(13)
    void findByUserIdIn() {
        orderRepository.save(order);
        List<Integer> userIds = Arrays.asList(1, 2, 3);
        List<za.ac.cput.domain.Order> found = orderService.findByUserIdIn(userIds);
        assertFalse(found.isEmpty());
        assertTrue(userIds.contains(found.get(0).getUserId()));
        System.out.println("Found by User IDs in list: " + found);
    }

    @Test
    @Order(14)
    void findByOrderStatusIn() {
        orderRepository.save(order);
        List<OrderStatus> statuses = Arrays.asList(OrderStatus.PENDING, OrderStatus.IN_PROGRESS);
        List<za.ac.cput.domain.Order> found = orderService.findByOrderStatusIn(statuses);
        assertFalse(found.isEmpty());
        assertTrue(statuses.contains(found.get(0).getOrderStatus()));
        System.out.println("Found by Order Statuses in list: " + found);
    }

    @Test
    @Order(15)
    void findByOrderItems_ProductId() {
        orderRepository.save(order);
        List<za.ac.cput.domain.Order> found = orderService.findByOrderItems_ProductId(1);
        assertFalse(found.isEmpty());
        System.out.println("Found by OrderItems Product ID: " + found);
    }

    @Test
    @Order(16)
    void findByOrderItems_Quantity() {
        orderRepository.save(order);
        List<za.ac.cput.domain.Order> found = orderService.findByOrderItems_Quantity(5);
        assertFalse(found.isEmpty());
        System.out.println("Found by OrderItems Quantity: " + found);
    }

    @Test
    @Order(17)
    void findByOrderItems_PricePerUnitGreaterThan() {
        orderRepository.save(order);
        List<za.ac.cput.domain.Order> found = orderService.findByOrderItems_PricePerUnitGreaterThan(20.0);
        assertFalse(found.isEmpty());
        System.out.println("Found by OrderItems Price Per Unit Greater Than: " + found);
    }

    @Test
    @Order(18)
    void findByOrderItems_PricePerUnitLessThan() {
        orderRepository.save(order);
        List<za.ac.cput.domain.Order> found = orderService.findByOrderItems_PricePerUnitLessThan(50.0);
        assertFalse(found.isEmpty());
        System.out.println("Found by OrderItems Price Per Unit Less Than: " + found);
    }

    @Test
    @Order(19)
    void findByOrderItems_PricePerUnitBetween() {
        orderRepository.save(order);
        List<za.ac.cput.domain.Order> found = orderService.findByOrderItems_PricePerUnitBetween(20.0, 40.0);
        assertFalse(found.isEmpty());
        System.out.println("Found by OrderItems Price Per Unit Between: " + found);
    }
}