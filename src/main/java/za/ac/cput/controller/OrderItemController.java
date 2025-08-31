
package za.ac.cput.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.domain.OrderItem;
import za.ac.cput.domain.OrderStatus;
import za.ac.cput.service.OrderItemService;

import java.util.List;

@RestController
@RequestMapping("/orderItem")
public class OrderItemController {

    private OrderItemService service;

    @Autowired
    public OrderItemController(OrderItemService service) {
        this.service = service;
    }

    @PostMapping("/create")
    public OrderItem create(@RequestBody OrderItem orderItem) {
        return service.create(orderItem);
    }

    @GetMapping("/read/{id}")
    public OrderItem read(@PathVariable Integer id) {
        return service.read(id);
    }

    @PutMapping("/update")
    public OrderItem update(@RequestBody OrderItem orderItem) {
        return service.update(orderItem);
    }

    @DeleteMapping("/delete/{id}")
    public boolean delete(@PathVariable Integer id) {
        return service.delete(id);
    }

    @GetMapping("/getAll")
    public List<OrderItem> getAll() {
        return service.getAll();
    }

    @GetMapping("/findByProductId/{productId}")
    public List<OrderItem> findByProductId(@PathVariable int productId) {
        return service.findByProductId(productId);
    }

    @GetMapping("/findByQuantity/{quantity}")
    public List<OrderItem> findByQuantity(@PathVariable int quantity) {
        return service.findByQuantity(quantity);
    }

    @GetMapping("/findByPricePerUnit/{pricePerUnit}")
    public List<OrderItem> findByPricePerUnit(@PathVariable double pricePerUnit) {
        return service.findByPricePerUnit(pricePerUnit);
    }

    @GetMapping("/findByPricePerUnitGreaterThan/{pricePerUnit}")
    public List<OrderItem> findByPricePerUnitGreaterThan(@PathVariable double pricePerUnit) {
        return service.findByPricePerUnitGreaterThan(pricePerUnit);
    }

    @GetMapping("/findByPricePerUnitLessThan/{pricePerUnit}")
    public List<OrderItem> findByPricePerUnitLessThan(@PathVariable double pricePerUnit) {
        return service.findByPricePerUnitLessThan(pricePerUnit);
    }

    @GetMapping("/findByPricePerUnitBetween/{minPrice}/{maxPrice}")
    public List<OrderItem> findByPricePerUnitBetween(@PathVariable double minPrice, @PathVariable double maxPrice) {
        return service.findByPricePerUnitBetween(minPrice, maxPrice);
    }

    @GetMapping("/findByQuantityGreaterThan/{quantity}")
    public List<OrderItem> findByQuantityGreaterThan(@PathVariable int quantity) {
        return service.findByQuantityGreaterThan(quantity);
    }

    @GetMapping("/findByQuantityLessThan/{quantity}")
    public List<OrderItem> findByQuantityLessThan(@PathVariable int quantity) {
        return service.findByQuantityLessThan(quantity);
    }

    @GetMapping("/findByQuantityBetween/{minQuantity}/{maxQuantity}")
    public List<OrderItem> findByQuantityBetween(@PathVariable int minQuantity, @PathVariable int maxQuantity) {
        return service.findByQuantityBetween(minQuantity, maxQuantity);
    }

    @PostMapping("/findByProductIdIn")
    public List<OrderItem> findByProductIdIn(@RequestBody List<Integer> productIds) {
        return service.findByProductIdIn(productIds);
    }

    @PostMapping("/findByQuantityIn")
    public List<OrderItem> findByQuantityIn(@RequestBody List<Integer> quantities) {
        return service.findByQuantityIn(quantities);
    }

    @GetMapping("/findByProductIdAndQuantity/{productId}/{quantity}")
    public List<OrderItem> findByProductIdAndQuantity(@PathVariable int productId, @PathVariable int quantity) {
        return service.findByProductIdAndQuantity(productId, quantity);
    }

    @GetMapping("/findByProductIdAndPricePerUnit/{productId}/{pricePerUnit}")
    public List<OrderItem> findByProductIdAndPricePerUnit(@PathVariable int productId, @PathVariable double pricePerUnit) {
        return service.findByProductIdAndPricePerUnit(productId, pricePerUnit);
    }

    @GetMapping("/findByQuantityAndPricePerUnit/{quantity}/{pricePerUnit}")
    public List<OrderItem> findByQuantityAndPricePerUnit(@PathVariable int quantity, @PathVariable double pricePerUnit) {
        return service.findByQuantityAndPricePerUnit(quantity, pricePerUnit);
    }

    @GetMapping("/findByProductIdAndQuantityGreaterThan/{productId}/{quantity}")
    public List<OrderItem> findByProductIdAndQuantityGreaterThan(@PathVariable int productId, @PathVariable int quantity) {
        return service.findByProductIdAndQuantityGreaterThan(productId, quantity);
    }

    @GetMapping("/findByProductIdAndPricePerUnitGreaterThan/{productId}/{pricePerUnit}")
    public List<OrderItem> findByProductIdAndPricePerUnitGreaterThan(@PathVariable int productId, @PathVariable double pricePerUnit) {
        return service.findByProductIdAndPricePerUnitGreaterThan(productId, pricePerUnit);
    }

    @GetMapping("/findByProductIdOrderByQuantity/{productId}")
    public List<OrderItem> findByProductIdOrderByQuantity(@PathVariable int productId) {
        return service.findByProductIdOrderByQuantity(productId);
    }

    @GetMapping("/findByProductIdOrderByPricePerUnit/{productId}")
    public List<OrderItem> findByProductIdOrderByPricePerUnit(@PathVariable int productId) {
        return service.findByProductIdOrderByPricePerUnit(productId);
    }

    @GetMapping("/findByQuantityOrderByPricePerUnit/{quantity}")
    public List<OrderItem> findByQuantityOrderByPricePerUnit(@PathVariable int quantity) {
        return service.findByQuantityOrderByPricePerUnit(quantity);
    }

    @GetMapping("/findAllByOrderByProductId")
    public List<OrderItem> findAllByOrderByProductId() {
        return service.findAllByOrderByProductId();
    }

    @GetMapping("/findAllByOrderByQuantityDesc")
    public List<OrderItem> findAllByOrderByQuantityDesc() {
        return service.findAllByOrderByQuantityDesc();
    }

    @GetMapping("/findAllByOrderByPricePerUnitDesc")
    public List<OrderItem> findAllByOrderByPricePerUnitDesc() {
        return service.findAllByOrderByPricePerUnitDesc();
    }

    @GetMapping("/findByOrder/orderId/{orderId}")
    public List<OrderItem> findByOrderOrderId(@PathVariable int orderId) {
        return service.findByOrder_OrderId(orderId);
    }

    @GetMapping("/findByOrder/userId/{userId}")
    public List<OrderItem> findByOrderUserId(@PathVariable int userId) {
        return service.findByOrder_UserId(userId);
    }

    @GetMapping("/findByOrder/orderStatus/{orderStatus}")
    public List<OrderItem> findByOrderOrderStatus(@PathVariable OrderStatus orderStatus) {
        return service.findByOrder_OrderStatus(orderStatus);
    }

    @GetMapping("/findByOrder/paymentId/{paymentId}")
    public List<OrderItem> findByOrderPaymentId(@PathVariable int paymentId) {
        return service.findByOrder_PaymentId(paymentId);
    }

    @GetMapping("/findByOrder/addressId/{addressId}")
    public List<OrderItem> findByOrderAddressId(@PathVariable int addressId) {
        return service.findByOrder_AddressId(addressId);
    }
}