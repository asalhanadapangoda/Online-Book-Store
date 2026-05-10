package com.bookstore.repository;

import com.bookstore.model.Review;
import com.bookstore.util.FileUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Repository
public class ReviewRepository {

    @Value("${data.path}")
    private String dataPath;

    private String getFilePath() {
        return dataPath + "reviews.txt";
    }

    public List<Review> findAll() {
        List<String> lines = FileUtil.readFile(getFilePath());
        List<Review> reviews = new ArrayList<>();
        for (String line : lines) {
            String[] parts = line.split("\\|");
            if (parts.length >= 7) {
                reviews.add(new Review(parts[0], parts[1], parts[2], parts[3], parts[4], Integer.parseInt(parts[5]), parts[6]));
            }
        }
        return reviews;
    }

    public List<Review> findByBookId(String bookId) {
        List<Review> bookReviews = new ArrayList<>();
        for (Review r : findAll()) {
            if (r.getBookId().equals(bookId)) {
                bookReviews.add(r);
            }
        }
        return bookReviews;
    }

    public void save(Review review) {
        if (review.getId() == null || review.getId().isEmpty()) {
            review.setId(UUID.randomUUID().toString());
        }
        
        List<Review> reviews = findAll();
        reviews.add(review);
        
        List<String> lines = new ArrayList<>();
        for (Review r : reviews) {
            lines.add(String.join("|", r.getId(), r.getBookId(), r.getUserId(), r.getUsername(), r.getComment(), String.valueOf(r.getRating()), r.getDate()));
        }
        FileUtil.writeFile(getFilePath(), lines);
    }

    public void delete(String id) {
        List<Review> reviews = findAll();
        reviews.removeIf(r -> r.getId().equals(id));
        List<String> lines = new ArrayList<>();
        for (Review r : reviews) {
            lines.add(String.join("|", r.getId(), r.getBookId(), r.getUserId(), r.getUsername(), r.getComment(), String.valueOf(r.getRating()), r.getDate()));
        }
        FileUtil.writeFile(getFilePath(), lines);
    }
}
