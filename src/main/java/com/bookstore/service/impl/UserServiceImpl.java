package com.bookstore.service.impl;

import com.bookstore.model.User;
import com.bookstore.model.Admin;
import com.bookstore.model.Customer;
import com.bookstore.repository.UserRepository;

import com.bookstore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @PostConstruct
    public void init() {
        if (userRepository.findAll().isEmpty()) {
            Admin admin = new Admin("1", "admin", "admin@bookstore.com", "admin123");
            userRepository.save(admin);
            
            Customer customer = new Customer("2", "user", "user@gmail.com", "user123", "John Doe", "123 Street, City", "1234567890");
            userRepository.save(customer);
        }
    }


    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public void registerUser(User user) {
        user.setRole("CUSTOMER");
        userRepository.save(user);
    }
    
    @Override
    public void saveUser(User user) {
        userRepository.save(user);
    }

    @Override
    public void deleteUser(String id) {
        userRepository.delete(id);
    }
}
