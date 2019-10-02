package com.nursultanturdaliev.moneytransferapp.repository;

import com.nursultanturdaliev.moneytransferapp.model.Currency;
import org.springframework.data.repository.CrudRepository;

public interface CurrencyRepository extends CrudRepository<Currency, Integer > {
    //Custom queries
}
