package com.bookstore.service;

import com.bookstore.model.Payment;
import java.util.List;

public interface PaymentService {
    List<Payment> getAllPayments();
    void processPayment(Payment payment);
    void updatePaymentStatus(String paymentId, String newStatus);
}
