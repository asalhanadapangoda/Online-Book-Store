package com.bookstore.service;

import com.bookstore.model.Order;
import java.util.List;

public interface OrderService {
    List<Order> getAllOrders();
    List<Order> getOrdersByUserId(String userId);
    void placeOrder(Order order);
    void updateOrderStatus(String orderId, String status);
}
