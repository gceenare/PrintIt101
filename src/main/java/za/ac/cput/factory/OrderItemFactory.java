/*
OrderItemFactory.java
OrderItem Factory class
Author: L Mbangata (222558156)
Date: 11 May 2025
*/
package za.ac.cput.factory;
import za.ac.cput.domain.Order;
import za.ac.cput.domain.OrderItem;
import za.ac.cput.util.Helper;
public class OrderItemFactory {
    public static OrderItem createOrderItem(int productId, int
            quantity, double pricePerUnit) {
        if (Helper.intIsNull(productId) || Helper.intIsNull(quantity)
                || Helper.doubleIsInvalid(pricePerUnit)) {
            return null;
        }
        return new OrderItem.Builder()
                .setProductId(productId)
                .setQuantity(quantity)
                .setPricePerUnit(pricePerUnit)
                .build();
    }
    public static OrderItem createOrderItemWithOrder(int productId, int
            quantity, double pricePerUnit, Order order) {
        if (Helper.intIsNull(productId) || Helper.intIsNull(quantity)
                || Helper.doubleIsInvalid(pricePerUnit) ||
                !Helper.areAllObjectsNotNull(order)) {
            return null;
        }
        return new OrderItem.Builder()
                .setProductId(productId)
                .setQuantity(quantity)
                .setPricePerUnit(pricePerUnit)
                .setOrder(order)
                .build();
    }
}