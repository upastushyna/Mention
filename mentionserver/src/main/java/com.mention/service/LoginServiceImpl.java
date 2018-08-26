package com.mention.service;

import com.mention.config.EmailService;
import com.mention.dto.UserDtoRq;
import com.mention.model.Profile;
import com.mention.model.User;
import com.mention.model.UserToken;
import com.mention.payload.ApiResponse;
import com.mention.payload.JwtAuthenticationResponse;
import com.mention.payload.LoginRequest;
import com.mention.repository.ProfileRepository;
import com.mention.repository.UserRepository;
import com.mention.repository.UserTokenRepository;
import com.mention.security.JwtTokenProvider;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class LoginServiceImpl implements LoginService {

  private AuthenticationManager authenticationManager;

  private JwtTokenProvider tokenProvider;

  private UserRepository userRepository;

  private ModelMapper modelMapper;

  private UserTokenRepository tokenRepository;

  private EmailService emailService;

  private ProfileRepository profileRepository;

  @Autowired
  PasswordEncoder passwordEncoder;

  public LoginServiceImpl(AuthenticationManager authenticationManager,
                          JwtTokenProvider tokenProvider,
                          UserRepository userRepository,
                          EmailService emailService,
                          UserTokenRepository tokenRepository,
                          ProfileRepository profileRepository) {
    this.authenticationManager = authenticationManager;
    this.tokenProvider = tokenProvider;
    this.userRepository = userRepository;
    this.modelMapper = new ModelMapper();
    this.emailService = emailService;
    this.tokenRepository = tokenRepository;
    this.profileRepository = profileRepository;
  }

  @Override
  public ResponseEntity<?> authenticateUser(LoginRequest loginRequest) {

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

  @Override
  public ResponseEntity<?> registerUser(UserDtoRq userDtoRq) {
    Optional<User> user = userRepository.findByUsername(userDtoRq.getUsername());
    if(user.isPresent()) {
      return new ResponseEntity(new ApiResponse(false, "Username is already taken!"),
          HttpStatus.BAD_REQUEST);
    }
    Optional<User> user1 = userRepository.findByEmail(userDtoRq.getEmail());

    if(user1.isPresent()) {
      return new ResponseEntity(new ApiResponse(false, "Email Address already in use!"),
          HttpStatus.BAD_REQUEST);
    }

    User newUser = modelMapper.map(userDtoRq, User.class);
    newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
    newUser.setActive(false);
    userRepository.save(newUser);
    UserToken userToken = new UserToken();
    userToken.setUser(newUser);
    String token = UUID.randomUUID().toString();
    userToken.setToken(token);
    tokenRepository.save(userToken);
    String message = "Thank you for registering in Mention! To finish your " +
        "registration, please follow the link: " +
        "http://localhost:3000/register/" + token;
    String to = newUser.getEmail();
    String subject = "Confirm your email";
    Profile profile = new Profile(newUser);
    profileRepository.save(profile);
    emailService.sendSimpleMessage(to, subject, message);

    return ResponseEntity.ok(new ApiResponse(true, "User registered successfully"));
  }
}
