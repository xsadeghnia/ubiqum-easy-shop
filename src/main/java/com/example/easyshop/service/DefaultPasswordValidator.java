package com.example.easyshop.service;

import org.springframework.stereotype.Service;

@Service
public class DefaultPasswordValidator implements PasswordValidator {
    @Override
    public boolean validate(String password) {
        if (password == null){
            throw new IllegalArgumentException("Password is null");
        }
        return password.length() >= 6;
    }
}
