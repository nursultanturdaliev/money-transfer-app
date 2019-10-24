package com.nursultanturdaliev.moneytransferapp.services;

public interface SecurityService {

    String findLoggedInUsername();

    public void autoLogin(String username, String password);
}
