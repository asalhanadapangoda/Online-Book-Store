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
            if (parts.length >= 8) {
                users.add(new User(parts[0], parts[1], parts[2], parts[3], parts[4], parts[5], parts[6], parts[7]));
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
            lines.add(String.join("|", u.getId(), u.getUsername(), u.getEmail(), u.getPassword(), u.getRole(), u.getFullName(), u.getAddress(), u.getPhoneNumber()));
        }
        FileUtil.writeFile(getFilePath(), lines);
    }
    
    public void delete(String id) {
        List<User> users = findAll();
        users.removeIf(u -> u.getId().equals(id));
        List<String> lines = new ArrayList<>();
        for (User u : users) {
            lines.add(String.join("|", u.getId(), u.getUsername(), u.getEmail(), u.getPassword(), u.getRole(), u.getFullName(), u.getAddress(), u.getPhoneNumber()));
        }
        FileUtil.writeFile(getFilePath(), lines);
    }
}
