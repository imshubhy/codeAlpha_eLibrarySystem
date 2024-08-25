package com.codealpha.intern.controller;

import com.codealpha.intern.model.Transaction;
import com.codealpha.intern.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @PostMapping("/borrow")
    public String borrowBook(@RequestParam Long userId, @RequestParam Long bookId) {
        return transactionService.borrowBook(userId, bookId);
    }

    @PostMapping("/return")
    public String returnBook(@RequestParam Long transactionId) {
        return transactionService.returnBook(transactionId);
    }

    @GetMapping("/user/{userId}")
    public List<Transaction> getTransactionsByUser(@PathVariable Long userId) {
        return transactionService.getTransactionsByUser(userId);
    }
}
