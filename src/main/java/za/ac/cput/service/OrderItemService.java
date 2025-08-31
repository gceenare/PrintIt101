package za.ac.cput.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.domain.OrderItem;
import za.ac.cput.domain.OrderStatus;
import za.ac.cput.repository.OrderItemRepository;

import java.util.List;

@Service
public class OrderItemService implements IOrderItemService {

    private OrderItemRepository repository;

    @Autowired
    public OrderItemService(OrderItemRepository repository) {
        this.repository = repository;
    }

    @Override
    public OrderItem create(OrderItem orderItem) {
        return repository.save(orderItem);
    }

    @Override
    public OrderItem read(Integer id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public OrderItem update(OrderItem orderItem) {
        return repository.save(orderItem);
    }

    @Override
    public boolean delete(Integer id) {
        repository.deleteById(id);
        return true;
    }

    @Override
    public List<OrderItem> getAll() {
        return repository.findAll();
    }

    @Override
    public List<OrderItem> findByProductId(int productId) {
        return repository.findByProductId(productId);
    }

    @Override
    public List<OrderItem> findByQuantity(int quantity) {
        return repository.findByQuantity(quantity);
    }

    @Override
    public List<OrderItem> findByPricePerUnit(double pricePerUnit) {
        return repository.findByPricePerUnit(pricePerUnit);
    }

    @Override
    public List<OrderItem> findByPricePerUnitGreaterThan(double pricePerUnit) {
        return repository.findByPricePerUnitGreaterThan(pricePerUnit);
    }

    @Override
    public List<OrderItem> findByPricePerUnitLessThan(double pricePerUnit) {
        return repository.findByPricePerUnitLessThan(pricePerUnit);
    }

    @Override
    public List<OrderItem> findByPricePerUnitBetween(double minPrice, double maxPrice) {
        return repository.findByPricePerUnitBetween(minPrice, maxPrice);
    }

    @Override
    public List<OrderItem> findByQuantityGreaterThan(int quantity) {
        return repository.findByQuantityGreaterThan(quantity);
    }

    @Override
    public List<OrderItem> findByQuantityLessThan(int quantity) {
        return repository.findByQuantityLessThan(quantity);
    }

    @Override
    public List<OrderItem> findByQuantityBetween(int minQuantity, int maxQuantity) {
        return repository.findByQuantityBetween(minQuantity, maxQuantity);
    }

    @Override
    public List<OrderItem> findByProductIdIn(List<Integer> productIds) {
        return repository.findByProductIdIn(productIds);
    }

    @Override
    public List<OrderItem> findByQuantityIn(List<Integer> quantities) {
        return repository.findByQuantityIn(quantities);
    }

    @Override
    public List<OrderItem> findByProductIdAndQuantity(int productId, int quantity) {
        return repository.findByProductIdAndQuantity(productId, quantity);
    }

    @Override
    public List<OrderItem> findByProductIdAndPricePerUnit(int productId, double pricePerUnit) {
        return repository.findByProductIdAndPricePerUnit(productId, pricePerUnit);
    }

    @Override
    public List<OrderItem> findByQuantityAndPricePerUnit(int quantity, double pricePerUnit) {
        return repository.findByQuantityAndPricePerUnit(quantity, pricePerUnit);
    }

    @Override
    public List<OrderItem> findByProductIdAndQuantityGreaterThan(int productId, int quantity) {
        return repository.findByProductIdAndQuantityGreaterThan(productId, quantity);
    }

    @Override
    public List<OrderItem> findByProductIdAndPricePerUnitGreaterThan(int productId, double pricePerUnit) {
        return repository.findByProductIdAndPricePerUnitGreaterThan(productId, pricePerUnit);
    }

    @Override
    public List<OrderItem> findByProductIdOrderByQuantity(int productId) {
        return repository.findByProductIdOrderByQuantity(productId);
    }

    @Override
    public List<OrderItem> findByProductIdOrderByPricePerUnit(int productId) {
        return repository.findByProductIdOrderByPricePerUnit(productId);
    }

    @Override
    public List<OrderItem> findByQuantityOrderByPricePerUnit(int quantity) {
        return repository.findByQuantityOrderByPricePerUnit(quantity);
    }

    @Override
    public List<OrderItem> findAllByOrderByProductId() {
        return repository.findAllByOrderByProductId();
    }

    @Override
    public List<OrderItem> findAllByOrderByQuantityDesc() {
        return repository.findAllByOrderByQuantityDesc();
    }

    @Override
    public List<OrderItem> findAllByOrderByPricePerUnitDesc() {
        return repository.findAllByOrderByPricePerUnitDesc();
    }

    @Override
    public List<OrderItem> findByOrder_OrderId(int orderId) {
        return repository.findByOrder_OrderId(orderId);
    }

    @Override
    public List<OrderItem> findByOrder_UserId(int userId) {
        return repository.findByOrder_UserId(userId);
    }

    @Override
    public List<OrderItem> findByOrder_OrderStatus(OrderStatus orderStatus) {
        return repository.findByOrder_OrderStatus(orderStatus);
    }

    @Override
    public List<OrderItem> findByOrder_PaymentId(int paymentId) {
        return repository.findByOrder_PaymentId(paymentId);
    }

    @Override
    public List<OrderItem> findByOrder_AddressId(int addressId) {
        return repository.findByOrder_AddressId(addressId);
    }
}