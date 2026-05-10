package com.bookstore.repository;

import com.bookstore.model.Payment;
import com.bookstore.util.FileUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Repository
public class PaymentRepository {

    @Value("${data.path}")
    private String dataPath;

    private String getFilePath() {
        return dataPath + "payments.txt";
    }

    public List<Payment> findAll() {
        List<String> lines = FileUtil.readFile(getFilePath());
        List<Payment> payments = new ArrayList<>();
        for (String line : lines) {
            String[] parts = line.split("\\|");
            if (parts.length >= 6) {
                payments.add(new Payment(parts[0], parts[1], Double.parseDouble(parts[2]), parts[3], parts[4], parts[5]));
            }
        }
        return payments;
    }

    public void save(Payment payment) {
        if (payment.getId() == null || payment.getId().isEmpty()) {
            payment.setId(UUID.randomUUID().toString().substring(0, 8));
        }
        
        List<Payment> payments = findAll();
        // Remove old version if it exists (for updates)
        payments.removeIf(p -> p.getId().equals(payment.getId()));
        payments.add(payment);
        
        List<String> lines = new ArrayList<>();
        for (Payment p : payments) {
            lines.add(String.join("|", p.getId(), p.getOrderId(), String.valueOf(p.getAmount()), p.getPaymentMethod(), p.getStatus(), p.getPaymentDate()));
        }
        FileUtil.writeFile(getFilePath(), lines);
    }
}
