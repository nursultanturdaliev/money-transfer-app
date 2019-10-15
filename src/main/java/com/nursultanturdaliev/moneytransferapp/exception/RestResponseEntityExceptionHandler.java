package com.nursultanturdaliev.moneytransferapp.exception;

import com.nursultanturdaliev.moneytransferapp.MoneyTransferAppApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;

@ControllerAdvice
class RestResponseEntityExceptionHandler
        extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {RecordConflictException.class})
    public final ResponseEntity<ErrorResponse> handleUserNotFoundException(RecordConflictException ex) {
        ArrayList<String> details = new ArrayList<String>();
        details.add(ex.getLocalizedMessage());
        ErrorResponse error = new ErrorResponse(MoneyTransferAppApplication.RECORD_CONFLICT, details);
        return new ResponseEntity<>(error, HttpStatus.CONFLICT);
    }
}

