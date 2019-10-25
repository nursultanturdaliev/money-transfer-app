package com.nursultanturdaliev.moneytransferapp.controller;

import com.nursultanturdaliev.moneytransferapp.services.VerificationTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class EmailController {

    @Autowired
    VerificationTokenService verificationTokenService;

    @GetMapping("/verify-email")
    @ResponseBody
    public String verifyEmail(String code) {
        return verificationTokenService.verifyEmail(code).getBody();
    }
}
