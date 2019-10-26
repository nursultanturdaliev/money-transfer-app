package com.nursultanturdaliev.moneytransferapp.services;

import com.nursultanturdaliev.moneytransferapp.exception.UserNotFoundException;
import com.nursultanturdaliev.moneytransferapp.model.PasswordResetToken;
import com.nursultanturdaliev.moneytransferapp.model.User;
import com.nursultanturdaliev.moneytransferapp.repository.PasswordResetTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class PasswordResetTokenService {

    @Autowired
    private PasswordResetTokenRepository passwordTokenRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private SendingMailService sendingMailService;

    public void createPasswordReset(String userEmail) throws UserNotFoundException {
        User user = userService.findUserByEmail(userEmail);
        if (user == null) {
            throw new UserNotFoundException();
        }

        PasswordResetToken passwordResetToken =  create(user);

        sendingMailService.sendPasswordResetTokenMail(user, passwordResetToken.getToken());

    }

    public PasswordResetToken create(User user) {
        PasswordResetToken passwordResetToken = new PasswordResetToken();
        passwordResetToken.setUser(user);
        passwordResetToken.setToken(UUID.randomUUID().toString());
        return passwordTokenRepository.save(passwordResetToken);
    }
}
