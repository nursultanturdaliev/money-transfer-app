package com.nursultanturdaliev.moneytransferapp.controller;

import com.nursultanturdaliev.moneytransferapp.exception.InvalidTokenException;
import com.nursultanturdaliev.moneytransferapp.services.ExpiredTokenException;
import com.nursultanturdaliev.moneytransferapp.services.VerificationTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class EmailController {

    @Autowired
    VerificationTokenService verificationTokenService;

    @GetMapping("/verify-email")
    public ResponseEntity<String> verifyEmail(String code) throws ExpiredTokenException, InvalidTokenException {
        verificationTokenService.verifyEmail(code);

        return ResponseEntity.ok("You have successfully verified your email address.");
    }
}
