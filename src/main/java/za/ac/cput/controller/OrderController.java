
package za.ac.cput.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.domain.Order;
import za.ac.cput.domain.OrderStatus;
import za.ac.cput.service.OrderService;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

    private OrderService service;

    @Autowired
    public OrderController(OrderService service) {
        this.service = service;
    }

    @PostMapping("/create")
    public Order create(@RequestBody Order order) {
        return service.create(order);
    }

    @GetMapping("/read/{id}")
    public Order read(@PathVariable Integer id) {
        return service.read(id);
    }

    @PutMapping("/update")
    public Order update(@RequestBody Order order) {
        return service.update(order);
    }

    @DeleteMapping("/delete/{id}")
    public boolean delete(@PathVariable Integer id) {
        return service.delete(id);
    }

    @GetMapping("/getAll")
    public List<Order> getAll() {
        return service.getAll();
    }

    @GetMapping("/findByUserId/{userId}")
    public List<Order> findByUserId(@PathVariable int userId) {
        return service.findByUserId(userId);
    }

    @GetMapping("/findByPaymentId/{paymentId}")
    public Order findByPaymentId(@PathVariable int paymentId) {
        return service.findByPaymentId(paymentId);
    }

    @GetMapping("/findByAddressId/{addressId}")
    public List<Order> findByAddressId(@PathVariable int addressId) {
        return service.findByAddressId(addressId);
    }

    @GetMapping("/findByOrderStatus/{orderStatus}")
    public List<Order> findByOrderStatus(@PathVariable OrderStatus orderStatus) {
        return service.findByOrderStatus(orderStatus);
    }

    @GetMapping("/findByUserIdAndOrderStatus/{userId}/{orderStatus}")
    public List<Order> findByUserIdAndOrderStatus(@PathVariable int userId, @PathVariable OrderStatus orderStatus) {
        return service.findByUserIdAndOrderStatus(userId, orderStatus);
    }

    @GetMapping("/findByUserIdOrderByOrderId/{userId}")
    public List<Order> findByUserIdOrderByOrderId(@PathVariable int userId) {
        return service.findByUserIdOrderByOrderId(userId);
    }

    @GetMapping("/findByOrderStatusOrderByOrderId/{orderStatus}")
    public List<Order> findByOrderStatusOrderByOrderId(@PathVariable OrderStatus orderStatus) {
        return service.findByOrderStatusOrderByOrderId(orderStatus);
    }

    @PostMapping("/findByUserIdIn")
    public List<Order> findByUserIdIn(@RequestBody List<Integer> userIds) {
        return service.findByUserIdIn(userIds);
    }

    @PostMapping("/findByOrderStatusIn")
    public List<Order> findByOrderStatusIn(@RequestBody List<OrderStatus> orderStatuses) {
        return service.findByOrderStatusIn(orderStatuses);
    }
    @PostMapping("/createWithItems")
    public Order createWithItems(@RequestBody Order order) {
        return service.create(order);
    }


    @GetMapping("/findByOrderItems/productId/{productId}")
    public List<Order> findByOrderItemsProductId(@PathVariable int productId) {
        return service.findByOrderItems_ProductId(productId);
    }

    @GetMapping("/findByOrderItems/orderItemId/{orderItemId}")
    public List<Order> findByOrderItemsOrderItemId(@PathVariable int orderItemId) {
        return service.findByOrderItems_OrderItemId(orderItemId);
    }

    @GetMapping("/findByOrderItems/quantity/{quantity}")
    public List<Order> findByOrderItemsQuantity(@PathVariable int quantity) {
        return service.findByOrderItems_Quantity(quantity);
    }

    @GetMapping("/findByOrderItems/pricePerUnitGreaterThan/{pricePerUnit}")
    public List<Order> findByOrderItemsPricePerUnitGreaterThan(@PathVariable double pricePerUnit) {
        return service.findByOrderItems_PricePerUnitGreaterThan(pricePerUnit);
    }

    @GetMapping("/findByOrderItems/pricePerUnitLessThan/{pricePerUnit}")
    public List<Order> findByOrderItemsPricePerUnitLessThan(@PathVariable double pricePerUnit) {
        return service.findByOrderItems_PricePerUnitLessThan(pricePerUnit);
    }

    @GetMapping("/findByOrderItems/pricePerUnitBetween/{minPrice}/{maxPrice}")
    public List<Order> findByOrderItemsPricePerUnitBetween(@PathVariable double minPrice, @PathVariable double maxPrice) {
        return service.findByOrderItems_PricePerUnitBetween(minPrice, maxPrice);
    }
}