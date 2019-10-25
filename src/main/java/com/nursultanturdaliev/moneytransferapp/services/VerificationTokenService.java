package com.nursultanturdaliev.moneytransferapp.services;

import com.nursultanturdaliev.moneytransferapp.exception.InvalidTokenException;
import com.nursultanturdaliev.moneytransferapp.model.User;
import com.nursultanturdaliev.moneytransferapp.model.VerificationToken;
import com.nursultanturdaliev.moneytransferapp.repository.UserRepository;
import com.nursultanturdaliev.moneytransferapp.repository.VerificationTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class VerificationTokenService {

    private UserRepository userRepository;
    private VerificationTokenRepository verificationTokenRepository;
    private SendingMailService sendingMailService;

    @Autowired
    public VerificationTokenService(UserRepository userRepository, VerificationTokenRepository verificationTokenRepository, SendingMailService sendingMailService) {
        this.userRepository = userRepository;
        this.verificationTokenRepository = verificationTokenRepository;
        this.sendingMailService = sendingMailService;
    }

    public void createVerification(User user) {

        VerificationToken verificationToken = verificationTokenRepository.findOneByUserEmail(user.getEmail());
        if (verificationToken == null) {
            verificationToken = new VerificationToken();
            verificationToken.setUser(user);
            verificationTokenRepository.save(verificationToken);
        }

        sendingMailService.sendVerificationMail(user, verificationToken.getToken());
    }

    public void verifyEmail(String token) throws InvalidTokenException, ExpiredTokenException {
        VerificationToken verificationToken = verificationTokenRepository.findOneByToken(token);
        if (verificationToken == null) {
            throw new InvalidTokenException();
        }

        if (verificationToken.getExpiredDateTime().isBefore(LocalDateTime.now())) {
            throw new ExpiredTokenException();
        }

        verificationToken.setConfirmedDateTime(LocalDateTime.now());
        verificationToken.setStatus(VerificationToken.STATUS_VERIFIED);
        verificationToken.getUser().setActive(true);
        verificationTokenRepository.save(verificationToken);

    }
}
