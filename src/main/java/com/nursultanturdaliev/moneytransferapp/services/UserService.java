package com.nursultanturdaliev.moneytransferapp.services;

import com.nursultanturdaliev.moneytransferapp.model.User;

public interface UserService {

    void save(User user);

    User findOneByUsername(String username);
}
