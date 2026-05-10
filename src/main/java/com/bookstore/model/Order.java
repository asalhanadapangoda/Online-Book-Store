package com.bookstore.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    private String id;
    private String userId;
    private String bookDetails; // Comma separated book titles/quantities
    private double totalPrice;
    private String status; // PENDING, SHIPPED, DELIVERED
    private String orderDate;
    private String shippingAddress;
    private String paymentMethod;
}
