package com.nursultanturdaliev.moneytransferapp.services;

import com.nursultanturdaliev.moneytransferapp.exception.InvalidTokenException;

public interface SecurityService {

    String findLoggedInUsername();

    public void autoLogin(String username, String password);

    void validatePasswordResetToken(long id, String token) throws ExpiredTokenException, InvalidTokenException;
}
