package com.nursultanturdaliev.moneytransferapp.unit;

import com.nursultanturdaliev.moneytransferapp.dto.TransactionDto;
import com.nursultanturdaliev.moneytransferapp.exception.NullValueException;
import com.nursultanturdaliev.moneytransferapp.model.Currency;
import com.nursultanturdaliev.moneytransferapp.model.Transaction;
import com.nursultanturdaliev.moneytransferapp.repository.CurrencyRepository;
import com.nursultanturdaliev.moneytransferapp.repository.TransactionRepository;
import com.nursultanturdaliev.moneytransferapp.services.TransactionService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class TransactionServiceTest {

    private TransactionRepository transactionRepository;
    private CurrencyRepository currencyRepository;

    @Before
    public void init() {
        transactionRepository = Mockito.mock(TransactionRepository.class)
        ;
        currencyRepository = Mockito.mock(CurrencyRepository.class);
    }

    @Test
    public void testCreateTransaction() throws NullValueException {

        TransactionService transactionService = new TransactionService(transactionRepository, currencyRepository);

        TransactionDto transactionDto = new TransactionDto();

        transactionDto.setAmount(1000L);
        transactionDto.setTransactionId("unique-transaction-123");
        transactionDto.setCurrencyCode("USD");

        Transaction mockTransaction = new Transaction();
        mockTransaction.setAmount(transactionDto.getAmount());
        mockTransaction.setTransactionId(transactionDto.getTransactionId());

        Currency currency = new Currency();

        when(transactionRepository.save(any(Transaction.class))).thenReturn(mockTransaction);
        when(currencyRepository.findOneByName("USD")).thenReturn(currency);


        Transaction transaction = transactionService.createTransaction(transactionDto);

        assertEquals(transaction.getAmount(), transactionDto.getAmount());
        assertEquals(transaction.getTransactionId(),transactionDto.getTransactionId());
    }
}
