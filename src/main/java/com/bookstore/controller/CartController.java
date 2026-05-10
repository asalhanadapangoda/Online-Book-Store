package com.bookstore.controller;

import com.bookstore.model.Cart;
import com.bookstore.model.User;
import com.bookstore.service.BookService;
import com.bookstore.service.CartService;
import com.bookstore.util.SessionUtil;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class CartController {

    @Autowired
    private CartService cartService;

    @Autowired
    private BookService bookService;

    @GetMapping("/cart")
    public String viewCart(HttpSession session, Model model) {
        User user = SessionUtil.getUser(session);
        if (user == null) return "redirect:/login";

        List<Cart> items = cartService.getCartByUserId(user.getId());
        List<Map<String, Object>> cartDetails = new ArrayList<>();
        double total = 0;

        for (Cart item : items) {
            Map<String, Object> detail = new HashMap<>();
            detail.put("book", bookService.getBookById(item.getBookId()));
            detail.put("quantity", item.getQuantity());
            detail.put("subtotal", bookService.getBookById(item.getBookId()).getPrice() * item.getQuantity());
            cartDetails.add(detail);
            total += (double) detail.get("subtotal");
        }

        model.addAttribute("cartItems", cartDetails);
        model.addAttribute("total", total);
        return "cart";
    }

    @PostMapping("/cart/add")
    public String addToCart(@RequestParam("bookId") String bookId, @RequestParam("quantity") int quantity, HttpSession session) {
        User user = SessionUtil.getUser(session);
        if (user == null) return "redirect:/login";
        
        // Admins cannot buy books
        if ("ADMIN".equalsIgnoreCase(user.getRole())) {
            return "redirect:/books";
        }

        cartService.addToCart(user.getId(), bookId, quantity);
        return "redirect:/cart";
    }

    @GetMapping("/cart/remove")
    public String removeFromCart(@RequestParam("bookId") String bookId, HttpSession session) {
        User user = SessionUtil.getUser(session);
        if (user == null) return "redirect:/login";

        cartService.removeFromCart(user.getId(), bookId);
        return "redirect:/cart";
    }
}
