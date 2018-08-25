package com.mention.controller;

import com.mention.payload.JwtAuthenticationResponse;
import com.mention.payload.LoginRequest;
import com.mention.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/login")
public class LoginController {

  private AuthenticationManager authenticationManager;

  private JwtTokenProvider tokenProvider;

  @Autowired
  PasswordEncoder passwordEncoder;

  @Autowired
  public LoginController(AuthenticationManager authenticationManager,
                         JwtTokenProvider tokenProvider) {
    this.authenticationManager = authenticationManager;
    this.tokenProvider = tokenProvider;
  }

  @PostMapping
  public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {


    Authentication authentication = authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(
            loginRequest.getUsernameOrEmail(),
            loginRequest.getPassword()
        )
    );

    SecurityContextHolder.getContext().setAuthentication(authentication);

    String jwt = tokenProvider.generateToken(authentication);
    return ResponseEntity.ok(new JwtAuthenticationResponse(jwt));
  }
}
