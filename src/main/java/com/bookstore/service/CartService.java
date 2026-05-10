package com.bookstore.service;

import com.bookstore.model.Cart;
import com.bookstore.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    public List<Cart> getCartByUserId(String userId) {
        return cartRepository.findByUserId(userId);
    }

    public void addToCart(String userId, String bookId, int quantity) {
        List<Cart> items = cartRepository.findByUserId(userId);
        Cart existing = items.stream().filter(i -> i.getBookId().equals(bookId)).findFirst().orElse(null);
        if (existing != null) {
            existing.setQuantity(existing.getQuantity() + quantity);
            cartRepository.save(existing);
        } else {
            cartRepository.save(new Cart(userId, bookId, quantity));
        }
    }

    public void removeFromCart(String userId, String bookId) {
        cartRepository.remove(userId, bookId);
    }

    public void clearCart(String userId) {
        cartRepository.clear(userId);
    }
}
