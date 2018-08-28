package com.mention.service;

import com.mention.dto.UserRq;
import com.mention.exceptions.UserNotConfirmedException;
import com.mention.dto.LoginRq;
import org.springframework.http.ResponseEntity;

public interface LoginService {

  public ResponseEntity<?> authenticateUser(LoginRq loginRq) throws UserNotConfirmedException;

  ResponseEntity<?> registerUser(UserRq userRq);

  ResponseEntity<?> confirmRegistration(String token);
}
