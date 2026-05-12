package com.bookstore.service;

import com.bookstore.model.Book;
import com.bookstore.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;
import java.util.List;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    @PostConstruct
    public void init() {
        if (bookRepository.findAll().isEmpty()) {
            bookRepository.save(new Book("1", "The Great Gatsby", "F. Scott Fitzgerald", "A story of wealth and love.",
                    15.99, "Classic"));
            bookRepository.save(new Book("2", "1984", "George Orwell", "A dystopian social science fiction novel.",
                    12.50, "Dystopian"));
            bookRepository.save(new Book("3", "The Hobbit", "J.R.R. Tolkien", "A fantasy novel and children's book.",
                    20.00, "Fantasy"));
            bookRepository.save(new Book("4", "To Kill a Mockingbird", "Harper Lee", "A novel about racial injustice.",
                    18.25, "Classic"));
        }
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Book getBookById(String id) {
        return bookRepository.findById(id);
    }

    public void saveBook(Book book) {
        bookRepository.save(book);
    }

    public void deleteBook(String id) {
        bookRepository.delete(id);
    }
}
