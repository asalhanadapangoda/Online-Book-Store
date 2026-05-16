package com.bookstore.repository;

import com.bookstore.model.Order;
import com.bookstore.util.FileUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Repository
public class OrderRepository {

    @Value("${data.path}")
    private String dataPath;

    private String getFilePath() {
        return dataPath + "orders.txt";
    }

    public List<Order> findAll() {
        List<String> lines = FileUtil.readFile(getFilePath());
        List<Order> orders = new ArrayList<>();
        for (String line : lines) {
            String[] parts = line.split("\\|");
            if (parts.length >= 8) {
                orders.add(new Order(parts[0], parts[1], parts[2], Double.parseDouble(parts[3]), parts[4], parts[5], parts[6], parts[7]));
            }
        }
        return orders;
    }

    public List<Order> findByUserId(String userId) {
        List<Order> orders = new ArrayList<>();
        for (Order order : findAll()) {
            if (order.getUserId().equals(userId)) {
                orders.add(order);
            }
        }
        return orders;
    }

    public Order findById(String id) {
        for (Order order : findAll()) {
            if (order.getId().equals(id)) {
                return order;
            }
        }
        return null;
    }

    public void save(Order order) {
        if (order.getId() == null || order.getId().isEmpty()) {
            order.setId(UUID.randomUUID().toString());
        }
        
        List<Order> orders = findAll();
        orders.removeIf(o -> o.getId().equals(order.getId()));
        orders.add(order);
        
        List<String> lines = new ArrayList<>();
        for (Order o : orders) {
            lines.add(String.join("|", o.getId(), o.getUserId(), o.getBookDetails(), String.valueOf(o.getTotalPrice()), o.getStatus(), o.getOrderDate(), o.getShippingAddress(), o.getPaymentMethod()));
        }
        FileUtil.writeFile(getFilePath(), lines);
    }
}
