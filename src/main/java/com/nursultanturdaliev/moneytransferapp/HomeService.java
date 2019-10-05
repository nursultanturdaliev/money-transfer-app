package com.nursultanturdaliev.moneytransferapp;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service("HomeService")
public class HomeService {

    public String welcome()
    {
        return  "Welcome Home!";
    }
}
