package com.nursultanturdaliev.moneytransferapp.repository;

import com.nursultanturdaliev.moneytransferapp.model.Transaction;
import org.springframework.data.repository.CrudRepository;
import org.springframework.security.access.prepost.PostFilter;

import java.util.List;

public interface TransactionRepository extends CrudRepository<Transaction, Long> {
    Iterable<Transaction> findByReceiverUserId(Long id);

    @PostFilter("filterObject.transactionId == authentication.name")
    List<Transaction> findAllByAmount(Long amount);
}
