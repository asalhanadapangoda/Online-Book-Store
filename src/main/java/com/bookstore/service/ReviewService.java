package com.bookstore.service;

import com.bookstore.model.Review;
import java.util.List;

public interface ReviewService {
    List<Review> getAllReviews();
    List<Review> getReviewsByBookId(String bookId);
    void addReview(Review review);
    void deleteReview(String id);
}
