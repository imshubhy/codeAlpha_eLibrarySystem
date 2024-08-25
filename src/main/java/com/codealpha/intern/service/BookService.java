package com.codealpha.intern.service;

import com.codealpha.intern.model.Book;
import com.codealpha.intern.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;

    public Book createBook(Book book) {
        return bookRepository.save(book);
    }

    public Book getBookById(Long id) {
        return bookRepository.findById(id).orElse(null);
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Book updateBookAvailability(Long id, boolean available) {
        Book book = getBookById(id);
        if (book != null) {
            book.setAvailable(available);
            return bookRepository.save(book);
        }
        return null;
    }
}
