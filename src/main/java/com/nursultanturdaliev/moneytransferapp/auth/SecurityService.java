package com.nursultanturdaliev.moneytransferapp.auth;

public interface SecurityService {

    String findLoggedInUsername();

    public void autoLogin(String username, String password);
}
