package com.nursultanturdaliev.moneytransferapp.controller;

import com.nursultanturdaliev.moneytransferapp.exception.UserNotFoundException;
import com.nursultanturdaliev.moneytransferapp.response.GenericResponse;
import com.nursultanturdaliev.moneytransferapp.services.*;
import com.nursultanturdaliev.moneytransferapp.validation.UserValidator;
import com.nursultanturdaliev.moneytransferapp.model.User;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
public class RegistrationController {

    @Autowired
    private UserService userService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private PasswordResetTokenService passwordResetTokenService;

    @Autowired
    private SendingMailService sendingMailService;

    @Autowired
    private UserValidator userValidator;
    private Logger logger = LoggerFactory.getLogger(RegistrationController.class);

    @Autowired
    private VerificationTokenService verificationTokenService;

    @Autowired
    private MessageSource messageSource;


    @GetMapping("/registration")
    public String registration(Model model) {
        model.addAttribute("user", new User());

        return "registration";
    }

    @PostMapping("/registration")
    public String registration(@ModelAttribute("user") User user, Errors errors) {

        logger.info("Received registration request");

        userValidator.validate(user, errors);

        if (errors.hasErrors()) {
            return "registration";
        }

        User savedUser = userService.save(user);

        verificationTokenService.createVerification(savedUser);

        return "redirect:/";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/forgot-password")
    public String forgotPassword() {
        return "forgot-password";
    }

    @RequestMapping(value = "/reset-password",
            method = RequestMethod.POST)
    public ResponseEntity<GenericResponse> resetPassword(HttpServletRequest request,
                                                         @RequestParam("email") String userEmail) throws UserNotFoundException {
        passwordResetTokenService.createPasswordReset(userEmail);

        GenericResponse genericResponse = new GenericResponse(
                messageSource.getMessage("message.resetPasswordEmail", null,
                        request.getLocale()));
        return ResponseEntity.status(HttpStatus.CREATED).body(genericResponse);
    }
}
