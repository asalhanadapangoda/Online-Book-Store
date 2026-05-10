package com.bookstore.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Review {
    private String id;
    private String bookId;
    private String userId;
    private String username;
    private String comment;
    private int rating; // 1-5
    private String date;
}
