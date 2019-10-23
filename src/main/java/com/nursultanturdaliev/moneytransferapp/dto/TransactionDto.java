package com.nursultanturdaliev.moneytransferapp.dto;

public class TransactionDto {

    private String transactionId;
    private Long amount;
    private String currencyCode;

    public String getTransactionId() {
        return transactionId;
    }

    public String getCurrencyCode() {
        return currencyCode == null ? "USD" : currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }


    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }
}
