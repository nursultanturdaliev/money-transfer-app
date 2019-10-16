package com.nursultanturdaliev.moneytransferapp.exception;

import com.nursultanturdaliev.moneytransferapp.MoneyTransferAppApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.NoSuchElementException;

@ControllerAdvice
public class UserExceptionHandler {


    @ExceptionHandler({NoSuchElementException.class})
    public ResponseEntity<ErrorResponse> userNotFound(NoSuchElementException ex) {
        ArrayList<String> details = new ArrayList<String>();
        details.add(ex.getLocalizedMessage());
        details.add("Resource not found");
        ErrorResponse error = new ErrorResponse("No such resource", details);

        return new ResponseEntity<ErrorResponse>(error, HttpStatus.NOT_FOUND);
    }
}
