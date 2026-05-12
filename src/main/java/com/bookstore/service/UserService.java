package com.bookstore.service;

import com.bookstore.model.User;
import java.util.List;

public interface UserService {
    List<User> getAllUsers();
    User getUserByEmail(String email);
    void registerUser(User user);
    void saveUser(User user);
    void deleteUser(String id);
}
