package za.ac.cput.factory;

import za.ac.cput.domain.OrderItem;

/*
OrderItemFactory.java
Factory class for OrderItem
Author: L Mbangata (222558156)
 */

public class OrderItemFactory {
    public static OrderItem createOrderItem(int orderItemId, int productId, int quantity, double pricePerUnit) {

        // Validation
        if (orderItemId <= 0) {
            return null;
        }

        if (productId <= 0) {
            return null;
        }

        if (quantity <= 0) {
            return null;
        }

        if (pricePerUnit <= 0.0) {
            return null;
        }

        return new OrderItem.Builder()
                .setOrderItemId(orderItemId)
                .setProductId(productId)
                .setQuantity(quantity)
                .setPricePerUnit(pricePerUnit)
                .build();
    }

    // Method to create a copy of an existing order item
    public static OrderItem createOrderItem(OrderItem existingOrderItem) {
        if (existingOrderItem == null) {
            return null;
        }

        return new OrderItem.Builder()
                .copy(existingOrderItem)
                .build();
    }

    // Method to create order item with modified quantity
    public static OrderItem createOrderItemWithQuantity(OrderItem existingOrderItem, int newQuantity) {
        if (existingOrderItem == null || newQuantity <= 0) {
            return null;
        }

        return new OrderItem.Builder()
                .copy(existingOrderItem)
                .setQuantity(newQuantity)
                .build();
    }

    // Method to create order item with modified price per unit
    public static OrderItem createOrderItemWithPrice(OrderItem existingOrderItem, double newPricePerUnit) {
        if (existingOrderItem == null || newPricePerUnit <= 0.0) {
            return null;
        }

        return new OrderItem.Builder()
                .copy(existingOrderItem)
                .setPricePerUnit(newPricePerUnit)
                .build();
    }

    // Method to create order item with modified quantity and price
    public static OrderItem createOrderItemWithQuantityAndPrice(OrderItem existingOrderItem,
                                                                int newQuantity, double newPricePerUnit) {
        if (existingOrderItem == null || newQuantity <= 0 || newPricePerUnit <= 0.0) {
            return null;
        }

        return new OrderItem.Builder()
                .copy(existingOrderItem)
                .setQuantity(newQuantity)
                .setPricePerUnit(newPricePerUnit)
                .build();
    }
}