package com.example.easyshop.service;

import org.springframework.stereotype.Service;

@Service
public class RandomLoginTokenGenerator implements LoginTokenGenerator {
    @Override
    public LoginToken generate(String username) {
        LoginToken loginToken = new LoginToken();
        loginToken.setValue(username + "XYZ");
        return loginToken;
    }
}
