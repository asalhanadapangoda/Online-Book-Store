package com.bookstore.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Payment {
    private String id;
    private String orderId;
    private double amount;
    private String paymentMethod;
    private String status;
    private String paymentDate;
}
