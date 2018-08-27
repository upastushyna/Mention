package com.mention.service;

import com.mention.dto.UserDtoRq;
import com.mention.exceptions.UserNotConfirmedException;
import com.mention.payload.LoginRequest;
import org.springframework.http.ResponseEntity;

public interface LoginService {

  public ResponseEntity<?> authenticateUser(LoginRequest loginRequest) throws UserNotConfirmedException;

  ResponseEntity<?> registerUser(UserDtoRq userDtoRq);

  ResponseEntity<?> confirmRegistration(String token);
}
