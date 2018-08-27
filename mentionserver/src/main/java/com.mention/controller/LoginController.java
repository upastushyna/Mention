package com.mention.controller;

import com.mention.dto.UserDtoRq;
import com.mention.exceptions.UserNotConfirmedException;
import com.mention.payload.LoginRequest;
import com.mention.service.LoginServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class LoginController {

  private LoginServiceImpl loginService;

  @Autowired
  public LoginController(LoginServiceImpl loginService) {
    this.loginService = loginService;
  }

  @PostMapping("/login")
  public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest)
      throws UserNotConfirmedException {
    return loginService.authenticateUser(loginRequest);
  }

  @PostMapping("/register/{token}")
  public ResponseEntity<?> confirmRegistration(@PathVariable String token) {
    return loginService.confirmRegistration(token);
  }

  @PostMapping("/register")
  public ResponseEntity<?> registerUser(@Valid @RequestBody UserDtoRq userDtoRq) {
    return loginService.registerUser(userDtoRq);
  }
}
