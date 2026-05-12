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
            if (parts.length >= 6) {
                books.add(new Book(parts[0], parts[1], parts[2], parts[3], Double.parseDouble(parts[4]), parts[5]));
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
            lines.add(String.join("|", b.getId(), b.getTitle(), b.getAuthor(), b.getDescription(),
                    String.valueOf(b.getPrice()), b.getCategory(), b.getImageUrl()));
        }
        FileUtil.writeFile(getFilePath(), lines);
    }

    public void delete(String id) {
        List<Book> books = findAll();
        books.removeIf(b -> b.getId().equals(id));
        List<String> lines = new ArrayList<>();
        for (Book b : books) {
            lines.add(String.join("|", b.getId(), b.getTitle(), b.getAuthor(), b.getDescription(),
                    String.valueOf(b.getPrice()), b.getCategory(), b.getImageUrl()));
        }
        FileUtil.writeFile(getFilePath(), lines);
    }
}
