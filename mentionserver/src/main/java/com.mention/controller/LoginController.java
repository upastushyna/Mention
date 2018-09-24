package com.mention.controller;

import com.mention.dto.ForgotPasswordRq;
import com.mention.dto.LoginRq;
import com.mention.dto.UserRq;
import com.mention.service.LoginServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
  public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRq loginRequest) {
    return loginService.authenticateUser(loginRequest);
  }

  @PostMapping("/register/{token}")
  public ResponseEntity<?> confirmRegistration(@PathVariable String token) {
    return loginService.confirmRegistration(token);
  }

  @PostMapping("/register")
  public ResponseEntity<?> registerUser(@Valid @RequestBody UserRq userDtoRq) {
    return loginService.registerUser(userDtoRq);
  }

  @PostMapping("/recover")
  public ResponseEntity<?> recoverPassword(@Valid @RequestBody ForgotPasswordRq forgotPasswordRq) {
    return loginService.recoverPassword(forgotPasswordRq);
  }
}