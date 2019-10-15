package com.nursultanturdaliev.moneytransferapp.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/controller-advice-exception-handler")
public class ControllerAdviceExceptionHandlerController {

    @GetMapping("/")
    public ResponseEntity<Object> fetchAll() throws RecordConflictException
    {
        throw new RecordConflictException("controller-advice-exception-hander");
    }
}
