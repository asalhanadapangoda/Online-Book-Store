package com.bookstore.service.impl;

import com.bookstore.model.Review;
import com.bookstore.repository.ReviewRepository;
import com.bookstore.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    @Override
    public List<Review> getAllReviews() {
        return reviewRepository.findAll();
    }

    @Override
    public List<Review> getReviewsByBookId(String bookId) {
        return reviewRepository.findByBookId(bookId);
    }

    @Override
    public void addReview(Review review) {
        reviewRepository.save(review);
    }

    @Override
    public void deleteReview(String id) {
        reviewRepository.delete(id);
    }
}
