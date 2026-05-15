package com.bookstore.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class EBook extends Book {

    private String bookLink;

    public EBook(String id, String title, String author, String description, double price, String category, String bookLink) {
        super(id, title, author, description, price, category, "EBOOK");
        this.bookLink = bookLink;
    }
}
