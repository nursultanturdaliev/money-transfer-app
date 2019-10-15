package com.nursultanturdaliev.moneytransferapp.responsestatusexception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;

@Controller
public class ResponseStatusExceptionController {

    @GetMapping(path = "/response-status-exception")
    public ResponseEntity<String> fetchAll() {
        throw new ResponseStatusException(
                HttpStatus.BANDWIDTH_LIMIT_EXCEEDED,
                "Custom bandwidth limit exceeded reason message");
    }
}