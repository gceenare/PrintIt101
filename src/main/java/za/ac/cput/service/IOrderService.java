package za.ac.cput.service;

import za.ac.cput.domain.Order;
import za.ac.cput.domain.OrderStatus;
import java.util.List;

public interface IOrderService extends IService<Order, Integer> {
    List<Order> getAll();
    List<Order> findByUserId(int userId);
    Order findByPaymentId(int paymentId);
    List<Order> findByAddressId(int addressId);
    List<Order> findByOrderStatus(OrderStatus orderStatus);
    List<Order> findByUserIdAndOrderStatus(int userId, OrderStatus orderStatus);
    List<Order> findByUserIdOrderByOrderId(int userId);
    List<Order> findByOrderStatusOrderByOrderId(OrderStatus orderStatus);
    List<Order> findByUserIdIn(List<Integer> userIds);
    List<Order> findByOrderStatusIn(List<OrderStatus> orderStatuses);
    List<Order> findByOrderItems_ProductId(int productId);
    List<Order> findByOrderItems_OrderItemId(int orderItemId);
    List<Order> findByOrderItems_Quantity(int quantity);
    List<Order> findByOrderItems_PricePerUnitGreaterThan(double pricePerUnit);
    List<Order> findByOrderItems_PricePerUnitLessThan(double pricePerUnit);
    List<Order> findByOrderItems_PricePerUnitBetween(double minPrice, double maxPrice);
}
