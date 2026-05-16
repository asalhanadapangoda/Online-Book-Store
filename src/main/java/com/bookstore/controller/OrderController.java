package com.bookstore.controller;

import com.bookstore.model.Cart;
import com.bookstore.model.Order;
import com.bookstore.model.User;
import com.bookstore.service.BookService;
import com.bookstore.service.CartService;
import com.bookstore.service.OrderService;
import com.bookstore.util.SessionUtil;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Controller
public class OrderController {

    @Autowired
    private OrderService orderService;
    @Autowired
    private CartService cartService;
    @Autowired
    private BookService bookService;

    @GetMapping("/checkout")
    public String checkoutPage(HttpSession session, Model model) {
        User user = SessionUtil.getUser(session);
        if (user == null) return "redirect:/login";
        
        model.addAttribute("user", user);
        return "checkout";
    }



    @PostMapping("/checkout")
    public String processCheckout(@RequestParam("shippingAddress") String shippingAddress, @RequestParam("paymentMethod") String paymentMethod, HttpSession session, Model model) {
        User user = SessionUtil.getUser(session);
        if (user == null) return "redirect:/login";

        List<Cart> items = cartService.getCartByUserId(user.getId());
        if (items.isEmpty()) return "redirect:/books";

        double total = items.stream().mapToDouble(i -> bookService.getBookById(i.getBookId()).getPrice() * i.getQuantity()).sum();
        String bookDetails = items.stream()
                .map(i -> bookService.getBookById(i.getBookId()).getTitle() + " (x" + i.getQuantity() + ")")
                .collect(Collectors.joining(", "));

        Order order = new Order();
        order.setId(UUID.randomUUID().toString().substring(0, 8));
        order.setUserId(user.getId());
        order.setBookDetails(bookDetails);
        order.setTotalPrice(total);
        order.setShippingAddress(shippingAddress);
        order.setPaymentMethod(paymentMethod);
        order.setOrderDate(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
        
        if ("CREDIT_CARD".equals(paymentMethod)) {
            order.setStatus("COMPLETED");
        } else {
            order.setStatus("PENDING");
        }
        
        orderService.placeOrder(order);
        cartService.clearCart(user.getId());
        return "redirect:/payment/process?orderId=" + order.getId();
    }

    @GetMapping("/my-orders")
    public String myOrders(HttpSession session, Model model) {
        User user = SessionUtil.getUser(session);
        if (user == null) return "redirect:/login";
        model.addAttribute("orders", orderService.getOrdersByUserId(user.getId()));
        return "orders";
    }
}
