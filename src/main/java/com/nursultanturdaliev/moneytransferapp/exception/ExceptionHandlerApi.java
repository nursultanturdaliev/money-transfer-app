package com.nursultanturdaliev.moneytransferapp.exception;

import com.nursultanturdaliev.moneytransferapp.model.Transaction;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;

import java.util.NoSuchElementException;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerApi {
    @ExceptionHandler(value = NoSuchElementException.class)
    public ResponseEntity<Object> handleNoSuchElementException() {
        return new ResponseEntity<>("Resource not Found", HttpStatus.NOT_FOUND);
    }
}
