package com.nursultanturdaliev.moneytransferapp;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class UserResourceNotFoundException extends Exception {
    public UserResourceNotFoundException() {
        super();
    }

    public UserResourceNotFoundException(String message){
        super(message);
    }
}
