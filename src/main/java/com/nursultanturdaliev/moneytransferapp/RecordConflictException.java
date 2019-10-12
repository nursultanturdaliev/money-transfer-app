package com.nursultanturdaliev.moneytransferapp;

public class RecordConflictException extends RuntimeException
{
    private static final long serialVersionUID = 1L;

    public RecordConflictException(String exception) {
        super(exception);
    }
}
