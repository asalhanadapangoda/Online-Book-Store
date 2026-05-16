package com.bookstore.controller;

import com.bookstore.model.Order;
import com.bookstore.model.Payment;
import com.bookstore.model.User;
import com.bookstore.service.OrderService;
import com.bookstore.service.PaymentService;
import com.bookstore.util.SessionUtil;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private OrderService orderService;

    @GetMapping("/payment/process")
    public String processPayment(@RequestParam("orderId") String orderId, HttpSession session) {
        User user = SessionUtil.getUser(session);
        if (user == null) return "redirect:/login";

        Order order = orderService.getOrderById(orderId);
        if (order != null && order.getUserId().equals(user.getId())) {
            // Process payment logic moved here
            if ("CREDIT_CARD".equals(order.getPaymentMethod())) {
                Payment payment = new Payment(null, order.getId(), order.getTotalPrice(), order.getPaymentMethod(), "COMPLETED", order.getOrderDate());
                paymentService.processPayment(payment);
                orderService.updateOrderStatus(orderId, "COMPLETED");
            }
            return "redirect:/payment/success?orderId=" + orderId;
        }
        
        return "redirect:/";
    }

    @GetMapping("/payment/success")
    public String paymentSuccess(@RequestParam("orderId") String orderId, HttpSession session, Model model) {
        User user = SessionUtil.getUser(session);
        if (user == null) return "redirect:/login";

        Order order = orderService.getOrderById(orderId);
        if (order != null && order.getUserId().equals(user.getId())) {
            model.addAttribute("order", order);
            return "payment";
        }
        
        return "redirect:/";
    }

    @GetMapping("/my-payments")
    public String myPayments(HttpSession session, Model model) {
        User user = SessionUtil.getUser(session);
        if (user == null) return "redirect:/login";
        
        // This could be used to show a payment history page if you create one
        model.addAttribute("payments", paymentService.getAllPayments());
        return "payment-history"; // You would need to create this JSP
    }
}
