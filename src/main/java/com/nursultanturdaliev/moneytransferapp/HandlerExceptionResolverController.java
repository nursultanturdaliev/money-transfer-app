package com.nursultanturdaliev.moneytransferapp;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping(path = "/default-handler-exception-resolver")
@Controller
public class HandlerExceptionResolverController {

    @GetMapping(path = "/")
    public ResponseEntity<Object> fetchAll() throws HttpMediaTypeNotAcceptableException
    {
        throw new HttpMediaTypeNotAcceptableException("custom http media type not acceptable exception");
    }
}
