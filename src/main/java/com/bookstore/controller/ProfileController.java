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
public class ProfileController {

    @Autowired
    private UserService userService;

    @GetMapping("/profile")
    public String profilePage(HttpSession session, Model model) {
        User user = SessionUtil.getUser(session);
        if (user == null) return "redirect:/login";
        model.addAttribute("user", user);
        return "profile";
    }

    @PostMapping("/profile/update")
    public String updateProfile(@RequestParam("fullName") String fullName,
                               @RequestParam("address") String address,
                               @RequestParam("phoneNumber") String phoneNumber,
                               @RequestParam("email") String email,
                               HttpSession session) {
        User currentUser = SessionUtil.getUser(session);
        if (currentUser == null) return "redirect:/login";
        
        currentUser.setEmail(email);
        if (currentUser instanceof com.bookstore.model.Customer) {
            com.bookstore.model.Customer customer = (com.bookstore.model.Customer) currentUser;
            customer.setFullName(fullName);
            customer.setAddress(address);
            customer.setPhoneNumber(phoneNumber);
        }
        
        userService.saveUser(currentUser);
        SessionUtil.setUser(session, currentUser);
        return "redirect:/profile?success";
    }

}
