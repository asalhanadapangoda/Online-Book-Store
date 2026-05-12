package com.bookstore.repository;

import com.bookstore.model.User;
import com.bookstore.util.FileUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Repository
public class UserRepository {

    @Value("${data.path}")
    private String dataPath;

    private String getFilePath() {
        return dataPath + "users.txt";
    }

    public List<User> findAll() {
        List<String> lines = FileUtil.readFile(getFilePath());
        List<User> users = new ArrayList<>();
        for (String line : lines) {
            String[] parts = line.split("\\|");
            if (parts.length >= 5) {
                String id = parts[0];
                String username = parts[1];
                String email = parts[2];
                String password = parts[3];
                String role = parts[4];
                
                if ("CUSTOMER".equalsIgnoreCase(role) && parts.length >= 8) {
                    users.add(new com.bookstore.model.Customer(id, username, email, password, parts[5], parts[6], parts[7]));
                } else if ("ADMIN".equalsIgnoreCase(role)) {
                    users.add(new com.bookstore.model.Admin(id, username, email, password));
                } else {
                    users.add(new User(id, username, email, password, role));
                }
            }
        }
        return users;
    }

    public User findByEmail(String email) {
        return findAll().stream()
                .filter(u -> u.getEmail().equalsIgnoreCase(email))
                .findFirst()
                .orElse(null);
    }

    public void save(User user) {
        if (user.getId() == null || user.getId().isEmpty()) {
            user.setId(UUID.randomUUID().toString());
        }
        
        List<User> users = findAll();
        users.removeIf(u -> u.getId().equals(user.getId()));
        users.add(user);
        
        List<String> lines = new ArrayList<>();
        for (User u : users) {
            String fullName = "";
            String address = "";
            String phoneNumber = "";
            
            if (u instanceof com.bookstore.model.Customer) {
                com.bookstore.model.Customer c = (com.bookstore.model.Customer) u;
                fullName = c.getFullName();
                address = c.getAddress();
                phoneNumber = c.getPhoneNumber();
            }
            
            lines.add(String.join("|", u.getId(), u.getUsername(), u.getEmail(), u.getPassword(), u.getRole(), fullName, address, phoneNumber));
        }
        FileUtil.writeFile(getFilePath(), lines);
    }
    
    public void delete(String id) {
        List<User> users = findAll();
        users.removeIf(u -> u.getId().equals(id));
        List<String> lines = new ArrayList<>();
        for (User u : users) {
            String fullName = "";
            String address = "";
            String phoneNumber = "";
            
            if (u instanceof com.bookstore.model.Customer) {
                com.bookstore.model.Customer c = (com.bookstore.model.Customer) u;
                fullName = c.getFullName();
                address = c.getAddress();
                phoneNumber = c.getPhoneNumber();
            }
            lines.add(String.join("|", u.getId(), u.getUsername(), u.getEmail(), u.getPassword(), u.getRole(), fullName, address, phoneNumber));
        }
        FileUtil.writeFile(getFilePath(), lines);
    }

}
