package com.example.bookmanagement.service;

import com.example.bookmanagement.model.Book;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class BookService {

    private final List<Book> books = new ArrayList<>();
    private final AtomicLong idGenerator = new AtomicLong(1);

    public BookService() {
        // Initialize with sample books
        books.add(new Book(idGenerator.getAndIncrement(), "Sạch - Clean Code", "Robert C. Martin"));
        books.add(new Book(idGenerator.getAndIncrement(), "Thiết kế mẫu - Design Patterns", "Gang of Four"));
        books.add(new Book(idGenerator.getAndIncrement(), "Java Hiệu Năng - Effective Java", "Joshua Bloch"));
        books.add(new Book(idGenerator.getAndIncrement(), "Lập trình hướng đối tượng", "Grady Booch"));
        books.add(new Book(idGenerator.getAndIncrement(), "RESTful API Design", "Leonard Richardson"));
    }

    public List<Book> getAllBooks() {
        return new ArrayList<>(books);
    }

    public void addBook(Book book) {
        book.setId(idGenerator.getAndIncrement());
        books.add(book);
    }

    public Book getBookById(Long id) {
        return books.stream()
                .filter(b -> b.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public void updateBook(Book book) {
        for (int i = 0; i < books.size(); i++) {
            if (books.get(i).getId().equals(book.getId())) {
                books.set(i, book);
                return;
            }
        }
    }

    public void deleteBook(Long id) {
        books.removeIf(b -> b.getId().equals(id));
    }
}
