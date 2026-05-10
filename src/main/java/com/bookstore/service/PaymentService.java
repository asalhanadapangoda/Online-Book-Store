package com.bookstore.service;

import com.bookstore.model.Payment;
import com.bookstore.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }

    public void processPayment(Payment payment) {
        paymentRepository.save(payment);
    }

    public void updatePaymentStatus(String paymentId, String newStatus) {
        List<Payment> payments = paymentRepository.findAll();
        for (Payment p : payments) {
            if (p.getId().equals(paymentId)) {
                p.setStatus(newStatus);
                paymentRepository.save(p);
                break;
            }
        }
    }
}
