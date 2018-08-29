package com.mention.service;

import com.mention.dto.LoginRq;
import com.mention.dto.UserRq;
import org.springframework.http.ResponseEntity;

public interface LoginService {

  ResponseEntity<?> authenticateUser(LoginRq loginRequest);

  ResponseEntity<?> registerUser(UserRq userDtoRq);

  ResponseEntity<?> confirmRegistration(String token);
}
