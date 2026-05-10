package com.bookstore.repository;

import com.bookstore.model.Cart;
import com.bookstore.util.FileUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class CartRepository {

    @Value("${data.path}")
    private String dataPath;

    private String getFilePath() {
        return dataPath + "cart.txt";
    }

    public List<Cart> findAll() {
        List<String> lines = FileUtil.readFile(getFilePath());
        List<Cart> cartItems = new ArrayList<>();
        for (String line : lines) {
            String[] parts = line.split("\\|");
            if (parts.length >= 3) {
                cartItems.add(new Cart(parts[0], parts[1], Integer.parseInt(parts[2])));
            }
        }
        return cartItems;
    }

    public List<Cart> findByUserId(String userId) {
        List<Cart> userCart = new ArrayList<>();
        for (Cart item : findAll()) {
            if (item.getUserId().equals(userId)) {
                userCart.add(item);
            }
        }
        return userCart;
    }

    public void save(Cart cart) {
        List<Cart> cartItems = findAll();
        boolean found = false;
        for (Cart item : cartItems) {
            if (item.getUserId().equals(cart.getUserId()) && item.getBookId().equals(cart.getBookId())) {
                item.setQuantity(cart.getQuantity());
                found = true;
                break;
            }
        }
        if (!found) {
            cartItems.add(cart);
        }
        saveAll(cartItems);
    }

    public void remove(String userId, String bookId) {
        List<Cart> cartItems = findAll();
        cartItems.removeIf(item -> item.getUserId().equals(userId) && item.getBookId().equals(bookId));
        saveAll(cartItems);
    }

    public void clear(String userId) {
        List<Cart> cartItems = findAll();
        cartItems.removeIf(item -> item.getUserId().equals(userId));
        saveAll(cartItems);
    }

    private void saveAll(List<Cart> items) {
        List<String> lines = new ArrayList<>();
        for (Cart item : items) {
            lines.add(String.join("|", item.getUserId(), item.getBookId(), String.valueOf(item.getQuantity())));
        }
        FileUtil.writeFile(getFilePath(), lines);
    }
}
