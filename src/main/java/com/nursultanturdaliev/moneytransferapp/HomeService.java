package com.nursultanturdaliev.moneytransferapp;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

@Service("HomeService")
@PreAuthorize("hasRole('ROLE_USER')")
public class HomeService {

    public String welcome()
    {
        return  "Welcome Home!";
    }
}
