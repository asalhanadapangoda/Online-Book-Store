package com.bookstore.service;

import com.bookstore.model.Review;
import com.bookstore.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    public List<Review> getAllReviews() {
        return reviewRepository.findAll();
    }

    public List<Review> getReviewsByBookId(String bookId) {
        return reviewRepository.findByBookId(bookId);
    }

    public void addReview(Review review) {
        reviewRepository.save(review);
    }

    public void deleteReview(String id) {
        reviewRepository.delete(id);
    }
}
