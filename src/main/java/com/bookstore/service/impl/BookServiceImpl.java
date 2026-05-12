package com.bookstore.service.impl;

import com.bookstore.model.Book;
import com.bookstore.repository.BookRepository;
import com.bookstore.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;
import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;

    @PostConstruct
    public void init() {
        if (bookRepository.findAll().isEmpty()) {
            bookRepository.save(new com.bookstore.model.PrintedBook("1", "The Great Gatsby", "F. Scott Fitzgerald", "A story of wealth and love.",
                    15.99, "Classic", 0.5));
            bookRepository.save(new com.bookstore.model.EBook("2", "1984", "George Orwell", "A dystopian social science fiction novel.",
                    12.50, "Dystopian", "https://example.com/1984.pdf"));
            bookRepository.save(new com.bookstore.model.PrintedBook("3", "The Hobbit", "J.R.R. Tolkien", "A fantasy novel and children's book.",
                    20.00, "Fantasy", 0.8));
            bookRepository.save(new com.bookstore.model.EBook("4", "To Kill a Mockingbird", "Harper Lee", "A novel about racial injustice.",
                    18.25, "Classic", "https://example.com/mockingbird.pdf"));

        }
    }


    @Override
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public Book getBookById(String id) {
        return bookRepository.findById(id);
    }

    @Override
    public void saveBook(Book book) {
        bookRepository.save(book);
    }

    @Override
    public void deleteBook(String id) {
        bookRepository.delete(id);
    }
}
