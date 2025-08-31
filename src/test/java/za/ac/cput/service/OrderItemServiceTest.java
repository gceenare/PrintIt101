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
import za.ac.cput.domain.OrderItem;
import za.ac.cput.domain.OrderStatus;
import za.ac.cput.factory.OrderItemFactory;
import za.ac.cput.repository.OrderItemRepository;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@Rollback
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class OrderItemServiceTest {

    @Autowired
    private OrderItemService orderItemService;

    @Autowired
    private OrderItemRepository orderItemRepository;

    private OrderItem orderItem;

    private OrderItem createOrderItem() {
        // Create sample order item using factory
        return OrderItemFactory.createOrderItem(
                1, // productId
                5, // quantity
                29.99 // pricePerUnit
        );
    }

    @BeforeEach
    void setUp() {
        orderItem = createOrderItem();
        assertNotNull(orderItem, "OrderItem creation failed in factory");
    }

    @Test
    @Order(1)
    void create() {
        OrderItem saved = orderItemService.create(orderItem);
        assertNotNull(saved);
        assertEquals(1, saved.getProductId());
        assertEquals(5, saved.getQuantity());
        assertEquals(29.99, saved.getPricePerUnit());
        assertTrue(saved.getOrderItemId() > 0);
        System.out.println("Created OrderItem: " + saved);
    }

    @Test
    @Order(2)
    void read() {
        OrderItem saved = orderItemRepository.save(orderItem);
        OrderItem found = orderItemService.read(saved.getOrderItemId());
        assertNotNull(found);
        assertEquals(1, found.getProductId());
        assertEquals(saved.getOrderItemId(), found.getOrderItemId());
        System.out.println("Read OrderItem: " + found);
    }

    @Test
    @Order(3)
    void update() {
        OrderItem saved = orderItemRepository.save(orderItem);
        OrderItem updatedOrderItem = new OrderItem.Builder()
                .copy(saved)
                .setQuantity(10)
                .setPricePerUnit(25.99)
                .setProductId(2)
                .build();
        OrderItem updated = orderItemService.update(updatedOrderItem);
        assertNotNull(updated);
        assertEquals(10, updated.getQuantity());
        assertEquals(25.99, updated.getPricePerUnit());
        assertEquals(2, updated.getProductId());
        assertEquals(saved.getOrderItemId(), updated.getOrderItemId());
        System.out.println("Updated OrderItem: " + updated);
    }

    @Test
    @Order(4)
    void delete() {
        OrderItem saved = orderItemRepository.save(orderItem);
        boolean deleted = orderItemService.delete(saved.getOrderItemId());
        assertTrue(deleted);
        assertNull(orderItemService.read(saved.getOrderItemId()));
        System.out.println("OrderItem deleted successfully");
    }

    @Test
    @Order(5)
    void getAll() {
        orderItemService.create(orderItem);
        List<OrderItem> orderItems = orderItemService.getAll();
        assertNotNull(orderItems);
        assertFalse(orderItems.isEmpty());
        System.out.println("All OrderItems: " + orderItems);
    }

    @Test
    @Order(6)
    void findByProductId() {
        orderItemRepository.save(orderItem);
        List<OrderItem> found = orderItemService.findByProductId(1);
        assertFalse(found.isEmpty());
        assertEquals(1, found.get(0).getProductId());
        System.out.println("Found by Product ID: " + found);
    }

    @Test
    @Order(7)
    void findByQuantity() {
        orderItemRepository.save(orderItem);
        List<OrderItem> found = orderItemService.findByQuantity(5);
        assertFalse(found.isEmpty());
        assertEquals(5, found.get(0).getQuantity());
        System.out.println("Found by Quantity: " + found);
    }

    @Test
    @Order(8)
    void findByPricePerUnit() {
        orderItemRepository.save(orderItem);
        List<OrderItem> found = orderItemService.findByPricePerUnit(29.99);
        assertFalse(found.isEmpty());
        assertEquals(29.99, found.get(0).getPricePerUnit());
        System.out.println("Found by Price Per Unit: " + found);
    }

    @Test
    @Order(9)
    void findByPricePerUnitGreaterThan() {
        orderItemRepository.save(orderItem);
        List<OrderItem> found = orderItemService.findByPricePerUnitGreaterThan(20.0);
        assertFalse(found.isEmpty());
        assertTrue(found.get(0).getPricePerUnit() > 20.0);
        System.out.println("Found by Price Per Unit Greater Than: " + found);
    }

    @Test
    @Order(10)
    void findByPricePerUnitLessThan() {
        orderItemRepository.save(orderItem);
        List<OrderItem> found = orderItemService.findByPricePerUnitLessThan(50.0);
        assertFalse(found.isEmpty());
        assertTrue(found.get(0).getPricePerUnit() < 50.0);
        System.out.println("Found by Price Per Unit Less Than: " + found);
    }

    @Test
    @Order(11)
    void findByPricePerUnitBetween() {
        orderItemRepository.save(orderItem);
        List<OrderItem> found = orderItemService.findByPricePerUnitBetween(20.0, 40.0);
        assertFalse(found.isEmpty());
        assertTrue(found.get(0).getPricePerUnit() >= 20.0 && found.get(0).getPricePerUnit() <= 40.0);
        System.out.println("Found by Price Per Unit Between: " + found);
    }

    @Test
    @Order(12)
    void findByQuantityGreaterThan() {
        orderItemRepository.save(orderItem);
        List<OrderItem> found = orderItemService.findByQuantityGreaterThan(3);
        assertFalse(found.isEmpty());
        assertTrue(found.get(0).getQuantity() > 3);
        System.out.println("Found by Quantity Greater Than: " + found);
    }

    @Test
    @Order(13)
    void findByQuantityLessThan() {
        orderItemRepository.save(orderItem);
        List<OrderItem> found = orderItemService.findByQuantityLessThan(10);
        assertFalse(found.isEmpty());
        assertTrue(found.get(0).getQuantity() < 10);
        System.out.println("Found by Quantity Less Than: " + found);
    }

    @Test
    @Order(14)
    void findByQuantityBetween() {
        orderItemRepository.save(orderItem);
        List<OrderItem> found = orderItemService.findByQuantityBetween(1, 10);
        assertFalse(found.isEmpty());
        assertTrue(found.get(0).getQuantity() >= 1 && found.get(0).getQuantity() <= 10);
        System.out.println("Found by Quantity Between: " + found);
    }

    @Test
    @Order(15)
    void findByProductIdIn() {
        orderItemRepository.save(orderItem);
        List<Integer> productIds = Arrays.asList(1, 2, 3);
        List<OrderItem> found = orderItemService.findByProductIdIn(productIds);
        assertFalse(found.isEmpty());
        assertTrue(productIds.contains(found.get(0).getProductId()));
        System.out.println("Found by Product IDs in list: " + found);
    }

    @Test
    @Order(16)
    void findByQuantityIn() {
        orderItemRepository.save(orderItem);
        List<Integer> quantities = Arrays.asList(5, 10, 15);
        List<OrderItem> found = orderItemService.findByQuantityIn(quantities);
        assertFalse(found.isEmpty());
        assertTrue(quantities.contains(found.get(0).getQuantity()));
        System.out.println("Found by Quantities in list: " + found);
    }

    @Test
    @Order(17)
    void findByProductIdAndQuantity() {
        orderItemRepository.save(orderItem);
        List<OrderItem> found = orderItemService.findByProductIdAndQuantity(1, 5);
        assertFalse(found.isEmpty());
        assertEquals(1, found.get(0).getProductId());
        assertEquals(5, found.get(0).getQuantity());
        System.out.println("Found by Product ID and Quantity: " + found);
    }

    @Test
    @Order(18)
    void findByProductIdAndPricePerUnit() {
        orderItemRepository.save(orderItem);
        List<OrderItem> found = orderItemService.findByProductIdAndPricePerUnit(1, 29.99);
        assertFalse(found.isEmpty());
        assertEquals(1, found.get(0).getProductId());
        assertEquals(29.99, found.get(0).getPricePerUnit());
        System.out.println("Found by Product ID and Price Per Unit: " + found);
    }

    @Test
    @Order(19)
    void findByQuantityAndPricePerUnit() {
        orderItemRepository.save(orderItem);
        List<OrderItem> found = orderItemService.findByQuantityAndPricePerUnit(5, 29.99);
        assertFalse(found.isEmpty());
        assertEquals(5, found.get(0).getQuantity());
        assertEquals(29.99, found.get(0).getPricePerUnit());
        System.out.println("Found by Quantity and Price Per Unit: " + found);
    }

    @Test
    @Order(20)
    void findByProductIdAndQuantityGreaterThan() {
        orderItemRepository.save(orderItem);
        List<OrderItem> found = orderItemService.findByProductIdAndQuantityGreaterThan(1, 3);
        assertFalse(found.isEmpty());
        assertEquals(1, found.get(0).getProductId());
        assertTrue(found.get(0).getQuantity() > 3);
        System.out.println("Found by Product ID and Quantity Greater Than: " + found);
    }

    @Test
    @Order(21)
    void findByProductIdAndPricePerUnitGreaterThan() {
        orderItemRepository.save(orderItem);
        List<OrderItem> found = orderItemService.findByProductIdAndPricePerUnitGreaterThan(1, 20.0);
        assertFalse(found.isEmpty());
        assertEquals(1, found.get(0).getProductId());
        assertTrue(found.get(0).getPricePerUnit() > 20.0);
        System.out.println("Found by Product ID and Price Per Unit Greater Than: " + found);
    }

    @Test
    @Order(22)
    void findByProductIdOrderByQuantity() {
        orderItemRepository.save(orderItem);
        List<OrderItem> found = orderItemService.findByProductIdOrderByQuantity(1);
        assertFalse(found.isEmpty());
        assertEquals(1, found.get(0).getProductId());
        System.out.println("Found by Product ID ordered by Quantity: " + found);
    }

    @Test
    @Order(23)
    void findByProductIdOrderByPricePerUnit() {
        orderItemRepository.save(orderItem);
        List<OrderItem> found = orderItemService.findByProductIdOrderByPricePerUnit(1);
        assertFalse(found.isEmpty());
        assertEquals(1, found.get(0).getProductId());
        System.out.println("Found by Product ID ordered by Price Per Unit: " + found);
    }

    @Test
    @Order(24)
    void findByQuantityOrderByPricePerUnit() {
        orderItemRepository.save(orderItem);
        List<OrderItem> found = orderItemService.findByQuantityOrderByPricePerUnit(5);
        assertFalse(found.isEmpty());
        assertEquals(5, found.get(0).getQuantity());
        System.out.println("Found by Quantity ordered by Price Per Unit: " + found);
    }

    @Test
    @Order(25)
    void findAllByOrderByProductId() {
        orderItemRepository.save(orderItem);
        List<OrderItem> found = orderItemService.findAllByOrderByProductId();
        assertFalse(found.isEmpty());
        System.out.println("Found all ordered by Product ID: " + found);
    }

    @Test
    @Order(26)
    void findAllByOrderByQuantityDesc() {
        orderItemRepository.save(orderItem);
        List<OrderItem> found = orderItemService.findAllByOrderByQuantityDesc();
        assertFalse(found.isEmpty());
        System.out.println("Found all ordered by Quantity Desc: " + found);
    }

    @Test
    @Order(27)
    void findAllByOrderByPricePerUnitDesc() {
        orderItemRepository.save(orderItem);
        List<OrderItem> found = orderItemService.findAllByOrderByPricePerUnitDesc();
        assertFalse(found.isEmpty());
        System.out.println("Found all ordered by Price Per Unit Desc: " + found);
    }

    @Test
    @Order(28)
    void findByOrder_OrderId() {
        // This test would require a saved Order with OrderItems
        // For now, just test the method exists and handles null gracefully
        List<OrderItem> found = orderItemService.findByOrder_OrderId(999); // Non-existent order
        assertNotNull(found);
        assertTrue(found.isEmpty());
        System.out.println("Found by Order ID: " + found);
    }

    @Test
    @Order(29)
    void findByOrder_UserId() {
        List<OrderItem> found = orderItemService.findByOrder_UserId(1);
        assertNotNull(found);
        System.out.println("Found by Order User ID: " + found);
    }

    @Test
    @Order(30)
    void findByOrder_OrderStatus() {
        List<OrderItem> found = orderItemService.findByOrder_OrderStatus(OrderStatus.PENDING);
        assertNotNull(found);
        System.out.println("Found by Order Status: " + found);
    }

    @Test
    @Order(31)
    void findByOrder_PaymentId() {
        List<OrderItem> found = orderItemService.findByOrder_PaymentId(1);
        assertNotNull(found);
        System.out.println("Found by Order Payment ID: " + found);
    }

    @Test
    @Order(32)
    void findByOrder_AddressId() {
        List<OrderItem> found = orderItemService.findByOrder_AddressId(1);
        assertNotNull(found);
        System.out.println("Found by Order Address ID: " + found);
    }
}