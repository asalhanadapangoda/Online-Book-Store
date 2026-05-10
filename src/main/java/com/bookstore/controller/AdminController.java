package com.bookstore.controller;

import com.bookstore.model.Book;
import com.bookstore.service.BookService;
import com.bookstore.service.OrderService;
import com.bookstore.service.ReviewService;
import com.bookstore.service.UserService;
import com.bookstore.service.PaymentService;
import com.bookstore.util.SessionUtil;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired private BookService bookService;
    @Autowired private UserService userService;
    @Autowired private OrderService orderService;
    @Autowired private ReviewService reviewService;
    @Autowired private PaymentService paymentService;

    @GetMapping("/dashboard")
    public String dashboard(HttpSession session, Model model) {
        if (!SessionUtil.isAdmin(session)) return "redirect:/login";
        model.addAttribute("totalBooks", bookService.getAllBooks().size());
        model.addAttribute("totalUsers", userService.getAllUsers().size());
        model.addAttribute("totalOrders", orderService.getAllOrders().size());
        model.addAttribute("totalReviews", reviewService.getAllReviews().size());
        model.addAttribute("totalAdmins", userService.getAllUsers().stream().filter(u -> "ADMIN".equals(u.getRole())).count());
        return "admin/dashboard";
    }

    @GetMapping("/manage-books")
    public String manageBooks(HttpSession session, Model model) {
        if (!SessionUtil.isAdmin(session)) return "redirect:/login";
        model.addAttribute("books", bookService.getAllBooks());
        return "admin/manage-books";
    }

    @GetMapping("/add-book")
    public String addBookPage(HttpSession session) {
        if (!SessionUtil.isAdmin(session)) return "redirect:/login";
        return "admin/add-book";
    }

    @PostMapping("/add-book")
    public String addBook(Book book, HttpSession session) {
        if (!SessionUtil.isAdmin(session)) return "redirect:/login";
        bookService.saveBook(book);
        return "redirect:/admin/manage-books";
    }

    @GetMapping("/delete-book/{id}")
    public String deleteBook(@PathVariable("id") String id, HttpSession session) {
        if (!SessionUtil.isAdmin(session)) return "redirect:/login";
        bookService.deleteBook(id);
        return "redirect:/admin/manage-books";
    }

    @GetMapping("/edit-book/{id}")
    public String editBookPage(@PathVariable("id") String id, HttpSession session, Model model) {
        if (!SessionUtil.isAdmin(session)) return "redirect:/login";
        model.addAttribute("book", bookService.getBookById(id));
        return "admin/edit-book";
    }

    @PostMapping("/edit-book")
    public String editBook(com.bookstore.model.Book book, HttpSession session) {
        if (!SessionUtil.isAdmin(session)) return "redirect:/login";
        bookService.saveBook(book); // Simple saveBook handles update because it uses the existing ID
        return "redirect:/admin/manage-books";
    }

    @GetMapping("/manage-users")
    public String manageUsers(HttpSession session, Model model) {
        if (!SessionUtil.isAdmin(session)) return "redirect:/login";
        model.addAttribute("users", userService.getAllUsers().stream()
                .filter(u -> u.getRole() != null && !u.getRole().trim().equalsIgnoreCase("ADMIN"))
                .toList());
        return "admin/manage-users";
    }

    @GetMapping("/manage-orders")
    public String manageOrders(HttpSession session, Model model) {
        if (!SessionUtil.isAdmin(session)) return "redirect:/login";
        model.addAttribute("orders", orderService.getAllOrders());
        return "admin/manage-orders";
    }


    @GetMapping("/manage-reviews")
    public String manageReviews(HttpSession session, Model model) {
        if (!SessionUtil.isAdmin(session)) return "redirect:/login";
        model.addAttribute("reviews", reviewService.getAllReviews());
        return "admin/manage-reviews";
    }

    @GetMapping("/manage-admins")
    public String manageAdmins(HttpSession session, Model model) {
        if (!SessionUtil.isAdmin(session)) return "redirect:/login";
        model.addAttribute("admins", userService.getAllUsers().stream().filter(u -> "ADMIN".equals(u.getRole())).toList());
        return "admin/manage-admins";
    }

    @PostMapping("/add-admin")
    public String addAdmin(com.bookstore.model.User user, HttpSession session) {
        if (!SessionUtil.isAdmin(session)) return "redirect:/login";
        user.setRole("ADMIN");
        userService.saveUser(user);
        return "redirect:/admin/manage-admins";
    }

    @GetMapping("/delete-user/{id}")
    public String deleteUser(@PathVariable("id") String id, HttpSession session) {
        if (!SessionUtil.isAdmin(session)) return "redirect:/login";
        userService.deleteUser(id);
        return "redirect:/admin/manage-users";
    }

    @GetMapping("/delete-admin/{id}")
    public String deleteAdmin(@PathVariable("id") String id, HttpSession session) {
        if (!SessionUtil.isAdmin(session)) return "redirect:/login";
        userService.deleteUser(id);
        return "redirect:/admin/manage-admins";
    }


    @PostMapping("/update-order-status")
    public String updateOrderStatus(@RequestParam("orderId") String orderId, @RequestParam("status") String status, HttpSession session) {
        if (!SessionUtil.isAdmin(session)) return "redirect:/login";
        orderService.updateOrderStatus(orderId, status);
        return "redirect:/admin/manage-orders";
    }
}
