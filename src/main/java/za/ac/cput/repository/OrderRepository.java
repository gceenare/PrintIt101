package za.ac.cput.repository;

import za.ac.cput.domain.Order;
import za.ac.cput.domain.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/*
OrderRepository.java
Repository interface for Order
Author: L Mbangata (222558156)
 */

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {

    List<Order> findByUserId(int userId);

    Optional<Order> findByPaymentId(int paymentId);

    List<Order> findByAddressId(int addressId);

    List<Order> findByOrderStatus(OrderStatus orderStatus);

    List<Order> findByUserIdAndOrderStatus(int userId, OrderStatus orderStatus);

    List<Order> findByUserIdOrderByOrderId(int userId);

    List<Order> findByOrderStatusOrderByOrderId(OrderStatus orderStatus);

    List<Order> findByUserIdIn(List<Integer> userIds);

    List<Order> findByOrderStatusIn(List<OrderStatus> orderStatuses);

    // Fixed: Changed from orderItem to orderItems (plural) to match your entity
    List<Order> findByOrderItems_ProductId(int productId);

    List<Order> findByOrderItems_OrderItemId(int orderItemId);

    List<Order> findByOrderItems_Quantity(int quantity);

    List<Order> findByOrderItems_PricePerUnitGreaterThan(double pricePerUnit);

    List<Order> findByOrderItems_PricePerUnitLessThan(double pricePerUnit);

    List<Order> findByOrderItems_PricePerUnitBetween(double minPrice, double maxPrice);
}