package com.nursultanturdaliev.moneytransferapp.exception;

import java.util.ArrayList;

public class ErrorResponse
{
    public ErrorResponse(String message, ArrayList<String> details) {
        super();
        this.message = message;
        this.details = details;
    }

    private String message;
    private ArrayList<String> details;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ArrayList<String> getDetails() {
        return details;
    }

    public void setDetails(ArrayList<String> details) {
        this.details = details;
    }
}
