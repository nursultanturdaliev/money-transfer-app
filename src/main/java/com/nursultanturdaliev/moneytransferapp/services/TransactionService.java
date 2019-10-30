package com.nursultanturdaliev.moneytransferapp.services;

import com.nursultanturdaliev.moneytransferapp.dto.TransactionDto;
import com.nursultanturdaliev.moneytransferapp.exception.NullValueException;
import com.nursultanturdaliev.moneytransferapp.model.Currency;
import com.nursultanturdaliev.moneytransferapp.model.Receiver;
import com.nursultanturdaliev.moneytransferapp.model.Transaction;
import com.nursultanturdaliev.moneytransferapp.repository.CurrencyRepository;
import com.nursultanturdaliev.moneytransferapp.repository.ReceiverRepository;
import com.nursultanturdaliev.moneytransferapp.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionService {

    private TransactionRepository transactionRepository;

    private CurrencyRepository currencyRepository;

    private ReceiverRepository receiverRepository;

    public TransactionService(TransactionRepository transactionRepository,
                              CurrencyRepository currencyRepository,
                              ReceiverRepository receiverRepository) {
        this.transactionRepository = transactionRepository;
        this.currencyRepository = currencyRepository;
        this.receiverRepository = receiverRepository;
    }

    public Transaction createTransaction(TransactionDto transactionDto, Receiver receiver) throws NullValueException {

        Transaction transaction = createFromDto(transactionDto, receiver);
        return transactionRepository.save(transaction);
    }

    private Transaction createFromDto(TransactionDto transactionDto, Receiver receiver) throws NullValueException {

        Transaction transaction = new Transaction();
        transaction.setAmount(transactionDto.getAmount());
        transaction.setTransactionId(transactionDto.getTransactionId());
        transaction.setPhoneNumber(transactionDto.getPhoneNumber());
        transaction.setReceiver(receiver);

        String currencyName = transactionDto.getCurrencyCode();
        Currency currency = currencyRepository.findOneByName(currencyName);
        if (currency == null) {
            throw new NullValueException();
        }

        transaction.setCurrency(currency);

        return transaction;
    }
}
