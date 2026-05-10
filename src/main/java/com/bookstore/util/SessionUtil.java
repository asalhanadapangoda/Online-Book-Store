package com.bookstore.util;

import com.bookstore.model.User;
import jakarta.servlet.http.HttpSession;

public class SessionUtil {

    public static void setUser(HttpSession session, User user) {
        session.setAttribute("user", user);
    }

    public static User getUser(HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            System.out.println("DEBUG: User NOT found in session " + session.getId());
        } else {
            System.out.println("DEBUG: User found in session: " + user.getEmail() + " (ID: " + session.getId() + ")");
        }
        return user;
    }

    public static boolean isLoggedIn(HttpSession session) {
        return getUser(session) != null;
    }

    public static boolean isAdmin(HttpSession session) {
        User user = getUser(session);
        return user != null && "ADMIN".equals(user.getRole());
    }

    public static void logout(HttpSession session) {
        session.invalidate();
    }
}
