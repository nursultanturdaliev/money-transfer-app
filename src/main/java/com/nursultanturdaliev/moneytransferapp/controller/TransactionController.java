package com.nursultanturdaliev.moneytransferapp.controller;

import com.nursultanturdaliev.moneytransferapp.annotation.IsSuperAdmin;
import com.nursultanturdaliev.moneytransferapp.dto.TransactionDto;
import com.nursultanturdaliev.moneytransferapp.exception.NullValueException;
import com.nursultanturdaliev.moneytransferapp.model.Transaction;
import com.nursultanturdaliev.moneytransferapp.repository.TransactionRepository;
import com.nursultanturdaliev.moneytransferapp.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping(path = "/api/transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private TransactionRepository transactionRepository;

    @GetMapping("/")
    @IsSuperAdmin
    public ResponseEntity<Iterable<Transaction>> findAll() {
        Iterable<Transaction> transactions = transactionRepository.findAll();
        if (transactions.iterator().hasNext()) {
            return ResponseEntity.ok(transactions);
        }
        return ResponseEntity.notFound().build();
    }


    @GetMapping("/{id}")
    public ResponseEntity<Transaction> findById(@PathVariable Long id) {
        Transaction transaction = transactionRepository.findById(id).get();
        return new ResponseEntity<>(transaction, HttpStatus.OK);

    }

    @PostMapping("/")
    public ResponseEntity<Transaction> create(@RequestBody TransactionDto transactionDto) throws NullValueException {
        Transaction transaction = transactionService.createTransaction(transactionDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(transaction);
    }
}
