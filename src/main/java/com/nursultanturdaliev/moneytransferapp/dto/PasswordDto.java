package com.nursultanturdaliev.moneytransferapp.dto;

import com.nursultanturdaliev.moneytransferapp.validation.ValidPassword;

public class PasswordDto {

    @ValidPassword
    private String newPassword;



    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

}
