package com.nursultanturdaliev.moneytransferapp.services;

import com.nursultanturdaliev.moneytransferapp.dto.TransactionDto;
import com.nursultanturdaliev.moneytransferapp.model.Currency;
import com.nursultanturdaliev.moneytransferapp.model.Transaction;
import com.nursultanturdaliev.moneytransferapp.repository.CurrencyRepository;
import com.nursultanturdaliev.moneytransferapp.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionService {

    @Autowired
    private
    TransactionRepository transactionRepository;

    @Autowired
    private
    CurrencyRepository currencyRepository;

    public Transaction createTransaction(TransactionDto transactionDto) {

        Transaction transaction = createFromDto(transactionDto);
        return transactionRepository.save(transaction);
    }

    private Transaction createFromDto(TransactionDto transactionDto) {

        Transaction transaction = new Transaction();
        transaction.setAmount(transactionDto.getAmount());
        transaction.setTransactionId(transactionDto.getTransactionId());

        String currencyName = transactionDto.getCurrencyCode();
        Currency currency = currencyRepository.findOneByName(currencyName);
        transaction.setCurrency(currency);

        return transaction;
    }
}