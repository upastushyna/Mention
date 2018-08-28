package com.mention.service;

import com.mention.dto.LoginRq;
import com.mention.dto.UserRq;
import com.mention.exceptions.UserNotConfirmedException;
import org.springframework.http.ResponseEntity;

public interface LoginService {

  public ResponseEntity<?> authenticateUser(LoginRq loginRequest) throws UserNotConfirmedException;

  ResponseEntity<?> registerUser(UserRq userDtoRq);

  ResponseEntity<?> confirmRegistration(String token);
}
