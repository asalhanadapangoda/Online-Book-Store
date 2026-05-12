package com.bookstore.repository;

import com.bookstore.model.Book;
import com.bookstore.util.FileUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Repository
public class BookRepository {

    @Value("${data.path}")
    private String dataPath;

    private String getFilePath() {
        return dataPath + "books.txt";
    }

    public List<Book> findAll() {
        List<String> lines = FileUtil.readFile(getFilePath());
        List<Book> books = new ArrayList<>();
        for (String line : lines) {
            String[] parts = line.split("\\|");
            if (parts.length >= 7) {
                String id = parts[0];
                String title = parts[1];
                String author = parts[2];
                String description = parts[3];
                double price = Double.parseDouble(parts[4]);
                String category = parts[5];
                String type = parts[6];
                
                if ("EBOOK".equals(type) && parts.length >= 8) {
                    books.add(new com.bookstore.model.EBook(id, title, author, description, price, category, parts[7]));
                } else if ("PRINTED".equals(type) && parts.length >= 8) {
                    books.add(new com.bookstore.model.PrintedBook(id, title, author, description, price, category, Double.parseDouble(parts[7])));
                } else {
                    books.add(new Book(id, title, author, description, price, category, type));
                }
            }
        }
        return books;
    }

    public Book findById(String id) {
        return findAll().stream()
                .filter(b -> b.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public void save(Book book) {
        if (book.getId() == null || book.getId().isEmpty()) {
            book.setId(UUID.randomUUID().toString());
        }

        List<Book> books = findAll();
        books.removeIf(b -> b.getId().equals(book.getId()));
        books.add(book);

        List<String> lines = new ArrayList<>();
        for (Book b : books) {
            String extra = "";
            if (b instanceof com.bookstore.model.EBook) {
                extra = ((com.bookstore.model.EBook) b).getBookLink();
            } else if (b instanceof com.bookstore.model.PrintedBook) {

                extra = String.valueOf(((com.bookstore.model.PrintedBook) b).getWeight());
            }
            
            lines.add(String.join("|", b.getId(), b.getTitle(), b.getAuthor(), b.getDescription(),
                    String.valueOf(b.getPrice()), b.getCategory(), b.getBookType(), extra));
        }
        FileUtil.writeFile(getFilePath(), lines);
    }

    public void delete(String id) {
        List<Book> books = findAll();
        books.removeIf(b -> b.getId().equals(id));
        List<String> lines = new ArrayList<>();
        for (Book b : books) {
             String extra = "";
            if (b instanceof com.bookstore.model.EBook) {
                extra = ((com.bookstore.model.EBook) b).getBookLink();
            } else if (b instanceof com.bookstore.model.PrintedBook) {

                extra = String.valueOf(((com.bookstore.model.PrintedBook) b).getWeight());
            }
            lines.add(String.join("|", b.getId(), b.getTitle(), b.getAuthor(), b.getDescription(),
                    String.valueOf(b.getPrice()), b.getCategory(), b.getBookType(), extra));
        }
        FileUtil.writeFile(getFilePath(), lines);
    }

}
