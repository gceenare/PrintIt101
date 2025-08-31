package za.ac.cput.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import za.ac.cput.domain.Order;
import za.ac.cput.domain.OrderItem;
import za.ac.cput.domain.OrderStatus;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class OrderRepositoryTest {

    @Autowired
    private OrderRepository orderRepository;

    private Order order1;
    private Order order2;
    private Order order3;
    private OrderItem orderItem1;
    private OrderItem orderItem2;
    private OrderItem orderItem3;

    @BeforeEach
    void setUp() {
        // Create OrderItems
        orderItem1 = new OrderItem.Builder()
                .setOrderItemId(1001)
                .setProductId(2001)
                .setQuantity(2)
                .setPricePerUnit(99.99)
                .build();

        orderItem2 = new OrderItem.Builder()
                .setOrderItemId(1002)
                .setProductId(2002)
                .setQuantity(1)
                .setPricePerUnit(149.50)
                .build();

        orderItem3 = new OrderItem.Builder()
                .setOrderItemId(1003)
                .setProductId(2003)
                .setQuantity(3)
                .setPricePerUnit(75.00)
                .build();

        // Create Orders
        order1 = new Order.Builder()
                .setUserId(101)
                .setPaymentId(501)
                .setAddressId(301)
                .setOrderItems(List.of(orderItem1))
                .setOrderStatus(OrderStatus.PENDING)
                .build();

        order2 = new Order.Builder()
                .setUserId(102)
                .setPaymentId(502)
                .setAddressId(302)
                .setOrderItems(List.of(orderItem2))
                .setOrderStatus(OrderStatus.IN_PROGRESS)
                .build();

        order3 = new Order.Builder()
                .setUserId(101)
                .setPaymentId(503)
                .setAddressId(303)
                .setOrderItems(List.of(orderItem3))
                .setOrderStatus(OrderStatus.COMPLETED)
                .build();

        // Save orders
        order1 = orderRepository.save(order1);
        order2 = orderRepository.save(order2);
        order3 = orderRepository.save(order3);
    }

    @Test
    void testFindByUserId() {
        List<Order> found = orderRepository.findByUserId(101);
        assertEquals(2, found.size());
        assertTrue(found.stream().allMatch(order -> order.getUserId() == 101));
    }

    @Test
    void testFindByUserIdNotFound() {
        List<Order> found = orderRepository.findByUserId(999);
        assertTrue(found.isEmpty());
    }

    @Test
    void testFindByPaymentId() {
        Optional<Order> found = orderRepository.findByPaymentId(501);
        assertTrue(found.isPresent());
        assertEquals(order1.getOrderId(), found.get().getOrderId());
    }

    @Test
    void testFindByPaymentIdNotFound() {
        Optional<Order> found = orderRepository.findByPaymentId(999);
        assertFalse(found.isPresent());
    }

    @Test
    void testFindByAddressId() {
        List<Order> found = orderRepository.findByAddressId(301);
        assertEquals(1, found.size());
        assertEquals(order1.getOrderId(), found.get(0).getOrderId());
    }

    @Test
    void testFindByOrderStatus() {
        List<Order> found = orderRepository.findByOrderStatus(OrderStatus.PENDING);
        assertEquals(1, found.size());
        assertEquals(order1.getOrderId(), found.get(0).getOrderId());
    }

    @Test
    void testFindByUserIdAndOrderStatus() {
        List<Order> found = orderRepository.findByUserIdAndOrderStatus(101, OrderStatus.PENDING);
        assertEquals(1, found.size());
        assertEquals(order1.getOrderId(), found.get(0).getOrderId());
    }

    @Test
    void testFindByUserIdOrderByOrderId() {
        List<Order> found = orderRepository.findByUserIdOrderByOrderId(101);
        assertEquals(2, found.size());
        assertTrue(found.get(0).getOrderId() <= found.get(1).getOrderId());
    }

    @Test
    void testFindByOrderStatusOrderByOrderId() {
        List<Order> found = orderRepository.findByOrderStatusOrderByOrderId(OrderStatus.PENDING);
        assertEquals(1, found.size());
    }

    @Test
    void testFindByUserIdIn() {
        List<Integer> userIds = List.of(101, 102);
        List<Order> found = orderRepository.findByUserIdIn(userIds);
        assertEquals(3, found.size());
        assertTrue(found.stream().allMatch(order -> userIds.contains(order.getUserId())));
    }

    @Test
    void testFindByOrderStatusIn() {
        List<OrderStatus> statuses = List.of(OrderStatus.PENDING, OrderStatus.COMPLETED);
        List<Order> found = orderRepository.findByOrderStatusIn(statuses);
        assertEquals(2, found.size());
        assertTrue(found.stream().allMatch(order -> statuses.contains(order.getOrderStatus())));
    }

    @Test
    void testSaveAndFindById() {
        OrderItem newItem = new OrderItem.Builder()
                .setOrderItemId(1004)
                .setProductId(2004)
                .setQuantity(1)
                .setPricePerUnit(299.99)
                .build();

        Order newOrder = new Order.Builder()
                .setUserId(103)
                .setPaymentId(504)
                .setAddressId(304)
                .setOrderItems(List.of(newItem))
                .setOrderStatus(OrderStatus.PENDING)
                .build();

        Order saved = orderRepository.save(newOrder);

        Optional<Order> found = orderRepository.findById(saved.getOrderId());
        assertTrue(found.isPresent());
        assertEquals(103, found.get().getUserId());
        assertEquals(OrderStatus.PENDING, found.get().getOrderStatus());
    }

    @Test
    void testDeleteById() {
        int idToDelete = order1.getOrderId();
        orderRepository.deleteById(idToDelete);
        Optional<Order> found = orderRepository.findById(idToDelete);
        assertFalse(found.isPresent());
    }
}
