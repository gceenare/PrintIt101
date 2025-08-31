package za.ac.cput.service;

import za.ac.cput.domain.OrderItem;
import za.ac.cput.domain.OrderStatus;
import java.util.List;

public interface IOrderItemService extends IService<OrderItem, Integer> {
    List<OrderItem> getAll();
    List<OrderItem> findByProductId(int productId);
    List<OrderItem> findByQuantity(int quantity);
    List<OrderItem> findByPricePerUnit(double pricePerUnit);
    List<OrderItem> findByPricePerUnitGreaterThan(double pricePerUnit);
    List<OrderItem> findByPricePerUnitLessThan(double pricePerUnit);
    List<OrderItem> findByPricePerUnitBetween(double minPrice, double maxPrice);
    List<OrderItem> findByQuantityGreaterThan(int quantity);
    List<OrderItem> findByQuantityLessThan(int quantity);
    List<OrderItem> findByQuantityBetween(int minQuantity, int maxQuantity);
    List<OrderItem> findByProductIdIn(List<Integer> productIds);
    List<OrderItem> findByQuantityIn(List<Integer> quantities);
    List<OrderItem> findByProductIdAndQuantity(int productId, int quantity);
    List<OrderItem> findByProductIdAndPricePerUnit(int productId, double pricePerUnit);
    List<OrderItem> findByQuantityAndPricePerUnit(int quantity, double pricePerUnit);
    List<OrderItem> findByProductIdAndQuantityGreaterThan(int productId, int quantity);
    List<OrderItem> findByProductIdAndPricePerUnitGreaterThan(int productId, double pricePerUnit);
    List<OrderItem> findByProductIdOrderByQuantity(int productId);
    List<OrderItem> findByProductIdOrderByPricePerUnit(int productId);
    List<OrderItem> findByQuantityOrderByPricePerUnit(int quantity);
    List<OrderItem> findAllByOrderByProductId();
    List<OrderItem> findAllByOrderByQuantityDesc();
    List<OrderItem> findAllByOrderByPricePerUnitDesc();
    List<OrderItem> findByOrder_OrderId(int orderId);
    List<OrderItem> findByOrder_UserId(int userId);
    List<OrderItem> findByOrder_OrderStatus(OrderStatus orderStatus);
    List<OrderItem> findByOrder_PaymentId(int paymentId);
    List<OrderItem> findByOrder_AddressId(int addressId);
}