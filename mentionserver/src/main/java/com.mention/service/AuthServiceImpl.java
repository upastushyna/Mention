package com.mention.service;

import com.mention.dto.LoginDetails;

public class AuthServiceImpl implements AuthService {
    @Override
    public boolean login(String username, String password) {
        return username.toUpperCase().equals(password);
    }

    @Override
    public boolean login(LoginDetails loginDetails) {
        return login(loginDetails.getUsername(), loginDetails.getPassword());
    }
}
