package com.nursultanturdaliev.moneytransferapp;

import com.nursultanturdaliev.moneytransferapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("HomeService")
public class HomeService {

    public String welcome()
    {
        return  "Welcome Home!";
    }
}
