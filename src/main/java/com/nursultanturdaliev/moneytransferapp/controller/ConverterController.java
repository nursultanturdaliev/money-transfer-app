package com.nursultanturdaliev.moneytransferapp.controller;

import com.nursultanturdaliev.moneytransferapp.response.CurrencyInfo;
import com.nursultanturdaliev.moneytransferapp.services.AkchabarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(path = "/api/converter")
public class ConverterController {

    @Autowired
    AkchabarService akchabarService;

    @GetMapping(value = "/")
    private ResponseEntity<CurrencyInfo> getConverter(@RequestParam() Long amount) {
        CurrencyInfo currencyInfo = akchabarService.getCurrencyInfo(amount);
        return ResponseEntity.ok(currencyInfo);
    }
}
