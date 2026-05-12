package com.bookstore.service;

import com.bookstore.model.Book;
import java.util.List;

public interface BookService {
    List<Book> getAllBooks();
    Book getBookById(String id);
    void saveBook(Book book);
    void deleteBook(String id);
}
