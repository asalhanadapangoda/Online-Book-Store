package com.bookstore.service.impl;

import com.bookstore.model.Order;
import com.bookstore.repository.OrderRepository;
import com.bookstore.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @Override
    public List<Order> getOrdersByUserId(String userId) {
        return orderRepository.findByUserId(userId);
    }

    @Override
    public void placeOrder(Order order) {
        if (order.getStatus() == null || order.getStatus().isEmpty()) {
            order.setStatus("PENDING");
        }
        orderRepository.save(order);
    }

    @Override
    public void updateOrderStatus(String orderId, String status) {
        List<Order> orders = orderRepository.findAll();
        for (Order o : orders) {
            if (o.getId().equals(orderId)) {
                o.setStatus(status);
                orderRepository.save(o);
                break;
            }
        }
    }
}
