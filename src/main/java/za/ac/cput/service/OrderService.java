package za.ac.cput.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.domain.Order;
import za.ac.cput.domain.OrderStatus;
import za.ac.cput.repository.OrderRepository;

import java.util.List;

@Service
public class OrderService implements IOrderService {

    private OrderRepository repository;

    @Autowired
    public OrderService(OrderRepository repository) {
        this.repository = repository;
    }

    @Override
    public Order create(Order order) {
        return repository.save(order);
    }

    @Override
    public Order read(Integer id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public Order update(Order order) {
        return repository.save(order);
    }

    @Override
    public boolean delete(Integer id) {
        repository.deleteById(id);
        return true;
    }

    @Override
    public List<Order> getAll() {
        return repository.findAll();
    }

    @Override
    public List<Order> findByUserId(int userId) {
        return repository.findByUserId(userId);
    }

    @Override
    public Order findByPaymentId(int paymentId) {
        return repository.findByPaymentId(paymentId).orElse(null);
    }

    @Override
    public List<Order> findByAddressId(int addressId) {
        return repository.findByAddressId(addressId);
    }

    @Override
    public List<Order> findByOrderStatus(OrderStatus orderStatus) {
        return repository.findByOrderStatus(orderStatus);
    }

    @Override
    public List<Order> findByUserIdAndOrderStatus(int userId, OrderStatus orderStatus) {
        return repository.findByUserIdAndOrderStatus(userId, orderStatus);
    }

    @Override
    public List<Order> findByUserIdOrderByOrderId(int userId) {
        return repository.findByUserIdOrderByOrderId(userId);
    }

    @Override
    public List<Order> findByOrderStatusOrderByOrderId(OrderStatus orderStatus) {
        return repository.findByOrderStatusOrderByOrderId(orderStatus);
    }

    @Override
    public List<Order> findByUserIdIn(List<Integer> userIds) {
        return repository.findByUserIdIn(userIds);
    }

    @Override
    public List<Order> findByOrderStatusIn(List<OrderStatus> orderStatuses) {
        return repository.findByOrderStatusIn(orderStatuses);
    }

    @Override
    public List<Order> findByOrderItems_ProductId(int productId) {
        return repository.findByOrderItems_ProductId(productId);
    }

    @Override
    public List<Order> findByOrderItems_OrderItemId(int orderItemId) {
        return repository.findByOrderItems_OrderItemId(orderItemId);
    }

    @Override
    public List<Order> findByOrderItems_Quantity(int quantity) {
        return repository.findByOrderItems_Quantity(quantity);
    }

    @Override
    public List<Order> findByOrderItems_PricePerUnitGreaterThan(double pricePerUnit) {
        return repository.findByOrderItems_PricePerUnitGreaterThan(pricePerUnit);
    }

    @Override
    public List<Order> findByOrderItems_PricePerUnitLessThan(double pricePerUnit) {
        return repository.findByOrderItems_PricePerUnitLessThan(pricePerUnit);
    }

    @Override
    public List<Order> findByOrderItems_PricePerUnitBetween(double minPrice, double maxPrice) {
        return repository.findByOrderItems_PricePerUnitBetween(minPrice, maxPrice);
    }
}