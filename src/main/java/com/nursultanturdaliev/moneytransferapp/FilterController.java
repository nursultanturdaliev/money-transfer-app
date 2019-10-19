package com.nursultanturdaliev.moneytransferapp;

import com.nursultanturdaliev.moneytransferapp.model.Transaction;
import com.nursultanturdaliev.moneytransferapp.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/api/transaction/filters")
public class FilterController {

    @Autowired
    TransactionRepository transactionRepository;

    @GetMapping("/")
    public @ResponseBody
    List<Transaction> fetchTransactions() {
        return transactionRepository.findAllByAmount(1000l);
    }
}
