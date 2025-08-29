package za.ac.cput.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import za.ac.cput.domain.OrderItem;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class OrderItemRepositoryTest {

    @Autowired
    private OrderItemRepository orderItemRepository;

    private OrderItem orderItem1;
    private OrderItem orderItem2;
    private OrderItem orderItem3;

    @BeforeEach
    void setUp() {
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
                .setProductId(2001) // Same product as orderItem1
                .setQuantity(3)
                .setPricePerUnit(89.99)
                .build();

        orderItem1 = orderItemRepository.save(orderItem1);
        orderItem2 = orderItemRepository.save(orderItem2);
        orderItem3 = orderItemRepository.save(orderItem3);
    }

    @Test
    void testFindByProductId() {
        List<OrderItem> found = orderItemRepository.findByProductId(2001);
        assertEquals(2, found.size());
        assertTrue(found.stream().allMatch(item -> item.getProductId() == 2001));
        System.out.println("Found order items by product ID 2001: " + found);
    }

    @Test
    void testFindByProductIdNotFound() {
        List<OrderItem> found = orderItemRepository.findByProductId(9999);
        assertTrue(found.isEmpty());
        System.out.println("No order items found for product ID 9999");
    }

    @Test
    void testFindByQuantity() {
        List<OrderItem> found = orderItemRepository.findByQuantity(2);
        assertEquals(1, found.size());
        assertEquals(1001, found.get(0).getOrderItemId());
        System.out.println("Found order items with quantity 2: " + found);
    }

    @Test
    void testFindByPricePerUnit() {
        List<OrderItem> found = orderItemRepository.findByPricePerUnit(99.99);
        assertEquals(1, found.size());
        assertEquals(1001, found.get(0).getOrderItemId());
        System.out.println("Found order items with price 99.99: " + found);
    }

    @Test
    void testFindByPricePerUnitGreaterThan() {
        List<OrderItem> found = orderItemRepository.findByPricePerUnitGreaterThan(100.0);
        assertEquals(1, found.size());
        assertEquals(149.50, found.get(0).getPricePerUnit());
        System.out.println("Found order items with price greater than 100.0: " + found);
    }

    @Test
    void testFindByPricePerUnitLessThan() {
        List<OrderItem> found = orderItemRepository.findByPricePerUnitLessThan(100.0);
        assertEquals(2, found.size());
        assertTrue(found.stream().allMatch(item -> item.getPricePerUnit() < 100.0));
        System.out.println("Found order items with price less than 100.0: " + found);
    }

    @Test
    void testFindByPricePerUnitBetween() {
        List<OrderItem> found = orderItemRepository.findByPricePerUnitBetween(90.0, 150.0);
        assertEquals(2, found.size());
        assertTrue(found.stream().allMatch(item -> item.getPricePerUnit() >= 90.0 && item.getPricePerUnit() <= 150.0));
        System.out.println("Found order items with price between 90.0 and 150.0: " + found);
    }

    @Test
    void testFindByQuantityGreaterThan() {
        List<OrderItem> found = orderItemRepository.findByQuantityGreaterThan(1);
        assertEquals(2, found.size());
        assertTrue(found.stream().allMatch(item -> item.getQuantity() > 1));
        System.out.println("Found order items with quantity greater than 1: " + found);
    }

    @Test
    void testFindByQuantityLessThan() {
        List<OrderItem> found = orderItemRepository.findByQuantityLessThan(3);
        assertEquals(2, found.size());
        assertTrue(found.stream().allMatch(item -> item.getQuantity() < 3));
        System.out.println("Found order items with quantity less than 3: " + found);
    }

    @Test
    void testFindByQuantityBetween() {
        List<OrderItem> found = orderItemRepository.findByQuantityBetween(1, 2);
        assertEquals(2, found.size());
        assertTrue(found.stream().allMatch(item -> item.getQuantity() >= 1 && item.getQuantity() <= 2));
        System.out.println("Found order items with quantity between 1 and 2: " + found);
    }

    @Test
    void testFindByProductIdAndQuantity() {
        List<OrderItem> found = orderItemRepository.findByProductIdAndQuantity(2001, 2);
        assertEquals(1, found.size());
        assertEquals(1001, found.get(0).getOrderItemId());
        System.out.println("Found order items with product ID 2001 and quantity 2: " + found);
    }

    @Test
    void testFindByProductIdAndPricePerUnit() {
        List<OrderItem> found = orderItemRepository.findByProductIdAndPricePerUnit(2001, 99.99);
        assertEquals(1, found.size());
        assertEquals(1001, found.get(0).getOrderItemId());
        System.out.println("Found order items with product ID 2001 and price 99.99: " + found);
    }

    @Test
    void testFindByQuantityAndPricePerUnit() {
        List<OrderItem> found = orderItemRepository.findByQuantityAndPricePerUnit(1, 149.50);
        assertEquals(1, found.size());
        assertEquals(1002, found.get(0).getOrderItemId());
        System.out.println("Found order items with quantity 1 and price 149.50: " + found);
    }

    @Test
    void testFindByProductIdAndQuantityGreaterThan() {
        List<OrderItem> found = orderItemRepository.findByProductIdAndQuantityGreaterThan(2001, 2);
        assertEquals(1, found.size());
        assertEquals(1003, found.get(0).getOrderItemId());
        System.out.println("Found order items with product ID 2001 and quantity greater than 2: " + found);
    }

    @Test
    void testFindByProductIdAndPricePerUnitGreaterThan() {
        List<OrderItem> found = orderItemRepository.findByProductIdAndPricePerUnitGreaterThan(2001, 90.0);
        assertEquals(1, found.size());
        assertEquals(1001, found.get(0).getOrderItemId());
        System.out.println("Found order items with product ID 2001 and price greater than 90.0: " + found);
    }

    @Test
    void testFindByProductIdOrderByQuantity() {
        List<OrderItem> found = orderItemRepository.findByProductIdOrderByQuantity(2001);
        assertEquals(2, found.size());
        assertTrue(found.get(0).getQuantity() <= found.get(1).getQuantity());
        System.out.println("Found order items with product ID 2001 ordered by quantity: " + found);
    }

    @Test
    void testFindByProductIdOrderByPricePerUnit() {
        List<OrderItem> found = orderItemRepository.findByProductIdOrderByPricePerUnit(2001);
        assertEquals(2, found.size());
        assertTrue(found.get(0).getPricePerUnit() <= found.get(1).getPricePerUnit());
        System.out.println("Found order items with product ID 2001 ordered by price: " + found);
    }

    @Test
    void testFindAllByOrderByProductId() {
        List<OrderItem> found = orderItemRepository.findAllByOrderByProductId();
        assertEquals(3, found.size());
        assertTrue(found.get(0).getProductId() <= found.get(1).getProductId());
        assertTrue(found.get(1).getProductId() <= found.get(2).getProductId());
        System.out.println("Found all order items ordered by product ID: " + found);
    }

    @Test
    void testFindAllByOrderByQuantityDesc() {
        List<OrderItem> found = orderItemRepository.findAllByOrderByQuantityDesc();
        assertEquals(3, found.size());
        assertTrue(found.get(0).getQuantity() >= found.get(1).getQuantity());
        assertTrue(found.get(1).getQuantity() >= found.get(2).getQuantity());
        System.out.println("Found all order items ordered by quantity descending: " + found);
    }

    @Test
    void testFindAllByOrderByPricePerUnitDesc() {
        List<OrderItem> found = orderItemRepository.findAllByOrderByPricePerUnitDesc();
        assertEquals(3, found.size());
        assertTrue(found.get(0).getPricePerUnit() >= found.get(1).getPricePerUnit());
        assertTrue(found.get(1).getPricePerUnit() >= found.get(2).getPricePerUnit());
        System.out.println("Found all order items ordered by price descending: " + found);
    }

    @Test
    void testSaveAndFindById() {
        OrderItem newOrderItem = new OrderItem.Builder()
                .setOrderItemId(1004)
                .setProductId(2003)
                .setQuantity(5)
                .setPricePerUnit(199.99)
                .build();

        OrderItem saved = orderItemRepository.save(newOrderItem);

        Optional<OrderItem> found = orderItemRepository.findById(saved.getOrderItemId());
        assertTrue(found.isPresent());
        assertEquals(2003, found.get().getProductId());
        assertEquals(5, found.get().getQuantity());
        assertEquals(199.99, found.get().getPricePerUnit());
        System.out.println("Saved and found order item: " + found.get());
    }

    @Test
    void testDeleteById() {
        orderItemRepository.deleteById(1001);
        Optional<OrderItem> found = orderItemRepository.findById(1001);
        assertFalse(found.isPresent());
        System.out.println("Order item with ID 1001 successfully deleted");
    }

    @Test
    void testCountByProductId() {
        List<OrderItem> found = orderItemRepository.findByProductId(2001);
        assertEquals(2, found.size());
        System.out.println("Count of order items for product ID 2001: " + found.size());
    }

    @Test
    void testFindByProductIdIn() {
        List<Integer> productIds = List.of(2001, 2002);
        List<OrderItem> found = orderItemRepository.findByProductIdIn(productIds);
        assertEquals(3, found.size());
        assertTrue(found.stream().allMatch(item -> productIds.contains(item.getProductId())));
        System.out.println("Found order items with product IDs in [2001, 2002]: " + found);
    }

    @Test
    void testFindByQuantityIn() {
        List<Integer> quantities = List.of(1, 3);
        List<OrderItem> found = orderItemRepository.findByQuantityIn(quantities);
        assertEquals(2, found.size());
        assertTrue(found.stream().allMatch(item -> quantities.contains(item.getQuantity())));
        System.out.println("Found order items with quantities in [1, 3]: " + found);
    }
}