package com.nursultanturdaliev.moneytransferapp.dto;

public class TransactionDto {

    private String transactionId;
    private Long amount;
    private String currencyCode;
    private String firstName;
    private String lastName;
    private String phoneNumber;

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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() { return lastName; }

    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getPhoneNumber() { return phoneNumber; }

    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }
}
