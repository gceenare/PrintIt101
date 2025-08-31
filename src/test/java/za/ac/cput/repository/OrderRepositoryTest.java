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
        // Create OrderItems first
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
                .setOrderId("ORD001")
                .setUserId(101)
                .setPaymentId(501)
                .setAddressId(301)
                .setOrderItem(orderItem1)
                .setOrderStatus(OrderStatus.PENDING)
                .build();

        order2 = new Order.Builder()
                .setOrderId("ORD002")
                .setUserId(102)
                .setPaymentId(502)
                .setAddressId(302)
                .setOrderItem(orderItem2)
                .setOrderStatus(OrderStatus.IN_PROGRESS)
                .build();

        order3 = new Order.Builder()
                .setOrderId("ORD003")
                .setUserId(101) // Same user as order1
                .setPaymentId(503)
                .setAddressId(303)
                .setOrderItem(orderItem3)
                .setOrderStatus(OrderStatus.COMPLETED)
                .build();

        order1 = orderRepository.save(order1);
        order2 = orderRepository.save(order2);
        order3 = orderRepository.save(order3);
    }

    @Test
    void testFindByUserId() {
        List<Order> found = orderRepository.findByUserId(101);
        assertEquals(2, found.size());
        assertTrue(found.stream().allMatch(order -> order.getUserId() == 101));
        System.out.println("Found orders by user ID 101: " + found);
    }

    @Test
    void testFindByUserIdNotFound() {
        List<Order> found = orderRepository.findByUserId(999);
        assertTrue(found.isEmpty());
        System.out.println("No orders found for user ID 999");
    }

    @Test
    void testFindByPaymentId() {
        Optional<Order> found = orderRepository.findByPaymentId(501);
        assertTrue(found.isPresent());
        assertEquals("ORD001", found.get().getOrderId());
        System.out.println("Found order by payment ID 501: " + found.get());
    }

    @Test
    void testFindByPaymentIdNotFound() {
        Optional<Order> found = orderRepository.findByPaymentId(999);
        assertFalse(found.isPresent());
        System.out.println("No order found for payment ID 999");
    }

    @Test
    void testFindByAddressId() {
        List<Order> found = orderRepository.findByAddressId(301);
        assertEquals(1, found.size());
        assertEquals("ORD001", found.get(0).getOrderId());
        System.out.println("Found orders by address ID 301: " + found);
    }

    @Test
    void testFindByOrderStatus() {
        List<Order> found = orderRepository.findByOrderStatus(OrderStatus.PENDING);
        assertEquals(1, found.size());
        assertEquals("ORD001", found.get(0).getOrderId());
        System.out.println("Found orders with PENDING status: " + found);
    }

    @Test
    void testFindByUserIdAndOrderStatus() {
        List<Order> found = orderRepository.findByUserIdAndOrderStatus(101, OrderStatus.PENDING);
        assertEquals(1, found.size());
        assertEquals("ORD001", found.get(0).getOrderId());
        System.out.println("Found orders for user 101 with PENDING status: " + found);
    }

    @Test
    void testFindByUserIdAndOrderStatusNotFound() {
        List<Order> found = orderRepository.findByUserIdAndOrderStatus(102, OrderStatus.PENDING);
        assertTrue(found.isEmpty());
        System.out.println("No orders found for user 102 with PENDING status");
    }

    @Test
    void testFindByUserIdOrderByOrderId() {
        List<Order> found = orderRepository.findByUserIdOrderByOrderId(101);
        assertEquals(2, found.size());
        assertTrue(found.get(0).getOrderId().compareTo(found.get(1).getOrderId()) <= 0);
        System.out.println("Found orders for user 101 ordered by order ID: " + found);
    }

    @Test
    void testFindByOrderStatusOrderByOrderId() {
        List<Order> found = orderRepository.findByOrderStatusOrderByOrderId(OrderStatus.PENDING);
        assertEquals(1, found.size());
        System.out.println("Found PENDING orders ordered by order ID: " + found);
    }

    @Test
    void testFindByUserIdIn() {
        List<Integer> userIds = List.of(101, 102);
        List<Order> found = orderRepository.findByUserIdIn(userIds);
        assertEquals(3, found.size());
        assertTrue(found.stream().allMatch(order -> userIds.contains(order.getUserId())));
        System.out.println("Found orders for users [101, 102]: " + found);
    }

    @Test
    void testFindByOrderStatusIn() {
        List<OrderStatus> statuses = List.of(OrderStatus.PENDING, OrderStatus.COMPLETED);
        List<Order> found = orderRepository.findByOrderStatusIn(statuses);
        assertEquals(2, found.size());
        assertTrue(found.stream().allMatch(order -> statuses.contains(order.getOrderStatus())));
        System.out.println("Found orders with status [PENDING, COMPLETED]: " + found);
    }

    @Test
    void testFindByOrderItem_ProductId() {
        List<Order> found = orderRepository.findByOrderItem_ProductId(2001);
        assertEquals(1, found.size());
        assertEquals("ORD001", found.get(0).getOrderId());
        System.out.println("Found orders with product ID 2001: " + found);
    }

    @Test
    void testFindByOrderItem_ProductIdNotFound() {
        List<Order> found = orderRepository.findByOrderItem_ProductId(9999);
        assertTrue(found.isEmpty());
        System.out.println("No orders found with product ID 9999");
    }

    @Test
    void testFindByOrderItem_OrderItemId() {
        Optional<Order> found = orderRepository.findByOrderItem_OrderItemId(1001);
        assertTrue(found.isPresent());
        assertEquals("ORD001", found.get().getOrderId());
        System.out.println("Found order with order item ID 1001: " + found.get());
    }

    @Test
    void testFindByOrderItem_OrderItemIdNotFound() {
        Optional<Order> found = orderRepository.findByOrderItem_OrderItemId(9999);
        assertFalse(found.isPresent());
        System.out.println("No order found with order item ID 9999");
    }

    @Test
    void testFindByOrderItem_Quantity() {
        List<Order> found = orderRepository.findByOrderItem_Quantity(2);
        assertEquals(1, found.size());
        assertEquals("ORD001", found.get(0).getOrderId());
        System.out.println("Found orders with quantity 2: " + found);
    }

    @Test
    void testFindByOrderItem_PricePerUnitGreaterThan() {
        List<Order> found = orderRepository.findByOrderItem_PricePerUnitGreaterThan(100.0);
        assertEquals(1, found.size());
        assertEquals("ORD002", found.get(0).getOrderId());
        System.out.println("Found orders with price per unit greater than 100.0: " + found);
    }

    @Test
    void testFindByOrderItem_PricePerUnitLessThan() {
        List<Order> found = orderRepository.findByOrderItem_PricePerUnitLessThan(100.0);
        assertEquals(2, found.size());
        assertTrue(found.stream().anyMatch(order -> order.getOrderId().equals("ORD001")));
        assertTrue(found.stream().anyMatch(order -> order.getOrderId().equals("ORD003")));
        System.out.println("Found orders with price per unit less than 100.0: " + found);
    }

    @Test
    void testFindByOrderItem_PricePerUnitBetween() {
        List<Order> found = orderRepository.findByOrderItem_PricePerUnitBetween(80.0, 150.0);
        assertEquals(2, found.size());
        assertTrue(found.stream().anyMatch(order -> order.getOrderId().equals("ORD001")));
        assertTrue(found.stream().anyMatch(order -> order.getOrderId().equals("ORD002")));
        System.out.println("Found orders with price per unit between 80.0 and 150.0: " + found);
    }

    @Test
    void testSaveAndFindById() {
        OrderItem newOrderItem = new OrderItem.Builder()
                .setOrderItemId(1004)
                .setProductId(2004)
                .setQuantity(1)
                .setPricePerUnit(299.99)
                .build();

        Order newOrder = new Order.Builder()
                .setOrderId("ORD004")
                .setUserId(103)
                .setPaymentId(504)
                .setAddressId(304)
                .setOrderItem(newOrderItem)
                .setOrderStatus(OrderStatus.PENDING)
                .build();

        Order saved = orderRepository.save(newOrder);

        Optional<Order> found = orderRepository.findById(saved.getOrderId());
        assertTrue(found.isPresent());
        assertEquals(103, found.get().getUserId());
        assertEquals(OrderStatus.PENDING, found.get().getOrderStatus());
        System.out.println("Saved and found order: " + found.get());
    }

    @Test
    void testDeleteById() {
        orderRepository.deleteById("ORD001");
        Optional<Order> found = orderRepository.findById("ORD001");
        assertFalse(found.isPresent());
        System.out.println("Order with ID ORD001 successfully deleted");
    }

    @Test
    void testCountByUserId() {
        List<Order> found = orderRepository.findByUserId(101);
        assertEquals(2, found.size());
        System.out.println("Count of orders for user 101: " + found.size());
    }

    @Test
    void testFindAllOrderStatuses() {
        List<Order> allOrders = orderRepository.findAll();
        assertEquals(3, allOrders.size());

        long pendingCount = allOrders.stream()
                .filter(order -> order.getOrderStatus() == OrderStatus.PENDING)
                .count();
        long inProgressCount = allOrders.stream()
                .filter(order -> order.getOrderStatus() == OrderStatus.IN_PROGRESS)
                .count();
        long completedCount = allOrders.stream()
                .filter(order -> order.getOrderStatus() == OrderStatus.COMPLETED)
                .count();

        assertEquals(1, pendingCount);
        assertEquals(1, inProgressCount);
        assertEquals(1, completedCount);
        System.out.println("Order status distribution - PENDING: " + pendingCount +
                ", IN_PROGRESS: " + inProgressCount + ", COMPLETED: " + completedCount);
    }

    @Test
    void testComplexQuery() {
        // Find all orders for user 101 with products having price less than 100
        List<Order> userOrders = orderRepository.findByUserId(101);
        List<Order> cheapOrders = orderRepository.findByOrderItem_PricePerUnitLessThan(100.0);

        // Orders that satisfy both conditions
        List<Order> complexResult = userOrders.stream()
                .filter(cheapOrders::contains)
                .toList();

        assertEquals(2, complexResult.size());
        System.out.println("Complex query result - User 101 orders with price < 100: " + complexResult);
    }
}