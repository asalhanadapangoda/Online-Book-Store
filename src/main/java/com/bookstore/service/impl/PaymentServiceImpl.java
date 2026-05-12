package com.bookstore.service.impl;

import com.bookstore.model.Payment;
import com.bookstore.repository.PaymentRepository;
import com.bookstore.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Override
    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }

    @Override
    public void processPayment(Payment payment) {
        paymentRepository.save(payment);
    }

    @Override
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
