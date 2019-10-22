package com.nursultanturdaliev.moneytransferapp;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

@Service("HomeService")
public class HomeService {

    public String welcome()
    {
        return  "Welcome Home!";
    }
}
