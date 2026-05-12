package com.bookstore.service;

import com.bookstore.model.Cart;
import java.util.List;

public interface CartService {
    List<Cart> getCartByUserId(String userId);
    void addToCart(String userId, String bookId, int quantity);
    void removeFromCart(String userId, String bookId);
    void clearCart(String userId);
}
