package com.nursultanturdaliev.moneytransferapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;

@SpringBootApplication
public class MoneyTransferAppApplication {

    public static final String   RECORD_CONFLICT = "RECORD_CONFLICT_CONSTANT";

    public static void main(String[] args) {
        SpringApplication.run(MoneyTransferAppApplication.class, args);
    }

}