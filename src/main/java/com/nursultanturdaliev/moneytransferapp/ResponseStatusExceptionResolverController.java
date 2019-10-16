package com.nursultanturdaliev.moneytransferapp;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/response-status-exception-resolver")
@Controller
public class ResponseStatusExceptionResolverController {

    @GetMapping("/")
    public ResponseEntity<Object> fetchAll() throws UserResourceNotFoundException {
        throw new UserResourceNotFoundException("this is custom error message");
    }
}
