package com.mention.service;

import com.mention.dto.LoginDetails;

public interface AuthService {
    boolean login(String username, String password);
    boolean login(LoginDetails loginDetails);
}
