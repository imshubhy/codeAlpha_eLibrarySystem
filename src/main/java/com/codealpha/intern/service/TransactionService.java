package com.codealpha.intern.service;

import com.codealpha.intern.model.Transaction;
import com.codealpha.intern.model.User;
import com.codealpha.intern.model.Book;
import com.codealpha.intern.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionService {
    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private BookService bookService;

    @Autowired
    private UserService userService;

    public String borrowBook(Long userId, Long bookId) {
        User user = userService.getUserById(userId);
        Book book = bookService.getBookById(bookId);

        if (user == null) {
            return "User not found.";
        }

        if (book == null || !book.isAvailable()) {
            return "Book not available.";
        }

        Transaction transaction = new Transaction();
        transaction.setUser(user);
        transaction.setBook(book);
        transactionRepository.save(transaction);

        bookService.updateBookAvailability(bookId, false);

        return "Book borrowed successfully.";
    }

    public String returnBook(Long transactionId) {
        Transaction transaction = transactionRepository.findById(transactionId).orElse(null);

        if (transaction == null || transaction.getReturnDate() != null) {
            return "Invalid transaction.";
        }

        transaction.setReturnDate(java.time.LocalDate.now());
        transactionRepository.save(transaction);

        bookService.updateBookAvailability(transaction.getBook().getId(), true);

        return "Book returned successfully.";
    }

    public List<Transaction> getTransactionsByUser(Long userId) {
        return transactionRepository.findByUserId(userId);
    }
}
