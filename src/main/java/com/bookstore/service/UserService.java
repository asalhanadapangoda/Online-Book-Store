package com.bookstore.service;

import com.bookstore.model.User;
import com.bookstore.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @PostConstruct
    public void init() {
        if (userRepository.findAll().isEmpty()) {
            User admin = new User("1", "admin", "admin@bookstore.com", "admin123", "ADMIN", "System Admin", "Main Office", "0000000000");
            userRepository.save(admin);
            
            User customer = new User("2", "user", "user@gmail.com", "user123", "CUSTOMER", "John Doe", "123 Street, City", "1234567890");
            userRepository.save(customer);
        }
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public void registerUser(User user) {
        user.setRole("CUSTOMER");
        userRepository.save(user);
    }
    
    public void saveUser(User user) {
        userRepository.save(user);
    }

    public void deleteUser(String id) {
        userRepository.delete(id);
    }
}
