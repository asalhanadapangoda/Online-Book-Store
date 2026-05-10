package com.bookstore.controller;

import com.bookstore.model.Review;
import com.bookstore.model.User;
import com.bookstore.service.ReviewService;
import com.bookstore.util.SessionUtil;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;

@Controller
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @PostMapping("/reviews/add")
    public String addReview(@RequestParam("bookId") String bookId, @RequestParam("comment") String comment, @RequestParam("rating") int rating, HttpSession session) {
        User user = SessionUtil.getUser(session);
        if (user == null) return "redirect:/login";

        Review review = new Review();
        review.setBookId(bookId);
        review.setUserId(user.getId());
        review.setUsername(user.getUsername());
        review.setComment(comment);
        review.setRating(rating);
        review.setDate(LocalDate.now().toString());
        
        reviewService.addReview(review);
        return "redirect:/books/" + bookId;
    }
}
