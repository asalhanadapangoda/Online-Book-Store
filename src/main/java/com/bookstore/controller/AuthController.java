package com.bookstore.controller;

import com.bookstore.model.User;
import com.bookstore.service.UserService;
import com.bookstore.util.SessionUtil;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthController {

    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam("email") String email, @RequestParam("password") String password, HttpSession session, Model model) {
        User user = userService.getUserByEmail(email);
        if (user != null && user.getPassword().equals(password)) {
            SessionUtil.setUser(session, user);
            if ("ADMIN".equals(user.getRole())) {
                return "redirect:/admin/dashboard";
            }
            return "redirect:/";
        }
        model.addAttribute("error", "Invalid email or password");
        return "login";
    }

    @GetMapping("/register")
    public String registerPage() {
        return "register";
    }

    @PostMapping("/register")
    public String register(@RequestParam("username") String username,
                          @RequestParam("email") String email,
                          @RequestParam("password") String password,
                          @RequestParam("fullName") String fullName,
                          @RequestParam("address") String address,
                          @RequestParam("phoneNumber") String phoneNumber,
                          Model model) {
        if (userService.getUserByEmail(email) != null) {
            model.addAttribute("error", "Email already exists");
            return "register";
        }
        
        com.bookstore.model.Customer customer = new com.bookstore.model.Customer(null, username, email, password, fullName, address, phoneNumber);
        userService.saveUser(customer);
        return "redirect:/login";
    }


    @GetMapping("/logout")
    public String logout(HttpSession session) {
        SessionUtil.logout(session);
        return "redirect:/";
    }
}
