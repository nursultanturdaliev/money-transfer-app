package com.nursultanturdaliev.moneytransferapp.exception;

import java.util.ArrayList;

public class ErrorResponse
{
    private String message;
    private ArrayList<String> details;

    public ErrorResponse(String message, ArrayList<String> details) {
        super();
        this.message = message;
        this.details = details;
    }

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
