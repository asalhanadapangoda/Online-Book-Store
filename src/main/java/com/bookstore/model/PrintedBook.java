package com.bookstore.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class PrintedBook extends Book {
    private double weight;

    public PrintedBook(String id, String title, String author, String description, double price, String category, double weight) {
        super(id, title, author, description, price, category, "PRINTED");
        this.weight = weight;
    }
}
