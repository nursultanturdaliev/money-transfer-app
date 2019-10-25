package com.nursultanturdaliev.moneytransferapp.services;

import com.nursultanturdaliev.moneytransferapp.model.User;

public interface UserService {

    User save(User user);

    User findOneByUsername(String username);
}
