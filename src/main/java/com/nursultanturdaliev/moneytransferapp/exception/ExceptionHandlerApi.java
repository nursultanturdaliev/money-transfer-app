package com.nursultanturdaliev.moneytransferapp.exception;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.nursultanturdaliev.moneytransferapp.model.Transaction;
import com.nursultanturdaliev.moneytransferapp.services.ExpiredTokenException;
import org.hibernate.exception.JDBCConnectionException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;

import java.util.NoSuchElementException;

import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerApi {
    @ExceptionHandler(value = {NoSuchElementException.class, EmptyResultDataAccessException.class})
    public ResponseEntity<Object> handleNoSuchElementException() {
        return new ResponseEntity<>("Resource not Found", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {JsonMappingException.class, NullValueException.class, InvalidTokenException.class})
    public ResponseEntity<Object> inputBadRequest() {
        return new ResponseEntity<>("Bad Request", HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler(value = JDBCConnectionException.class)
    public ResponseEntity<Object> internalError() {
        return new ResponseEntity<>("Internal Server Error", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<Object> methodNotAllowed() {
        return new ResponseEntity<>("Method not Allowed", HttpStatus.METHOD_NOT_ALLOWED);
    }

    @ExceptionHandler(value = ExpiredTokenException.class)
    public ResponseEntity<String> unproccessableEntity() {
        return ResponseEntity.unprocessableEntity().body("Expired Token");
    }
}
