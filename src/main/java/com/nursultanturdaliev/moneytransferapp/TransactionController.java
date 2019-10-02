package com.nursultanturdaliev.moneytransferapp;

import com.nursultanturdaliev.moneytransferapp.model.Transaction;
import com.nursultanturdaliev.moneytransferapp.model.User;
import com.nursultanturdaliev.moneytransferapp.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@RequestMapping(path = "/api/transactions")
public class TransactionController {

    @Autowired
    TransactionRepository transactionRepository;

    @GetMapping("/")
    public ResponseEntity<Iterable<Transaction>> all() {
        Iterable<Transaction> transactions = transactionRepository.findAll();

        if (!transactions.iterator().hasNext()) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

        return ResponseEntity.ok(transactions);
    }

    @GetMapping(path = {"/{id}/"})
    public ResponseEntity<Transaction> findById(@PathVariable Long id) {
        Optional<Transaction> optionalTransaction = transactionRepository.findById(id);
        if (!optionalTransaction.isPresent()) {
            return ResponseEntity.notFound().header("new-header","header-value").build();
        }

        Transaction transaction = optionalTransaction.get();

        return ResponseEntity.ok(transaction);
    }

}
