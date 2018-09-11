package com.mention.service;

import com.mention.dto.ApiRs;
import com.mention.dto.CurrentUserRs;
import com.mention.dto.ShortUserDetailsRs;
import com.mention.dto.UserIdRq;
import com.mention.dto.UserRq;
import com.mention.repository.UserRepository;
import com.mention.model.User;
import com.mention.security.UserPrincipal;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {


  private UserRepository userRepository;

  private ModelMapper modelMapper;

  @Autowired
  public UserServiceImpl(UserRepository userRepository) {
    this.userRepository = userRepository;
    this.modelMapper = new ModelMapper();
  }

  @Override
  public ShortUserDetailsRs getUser(String username) {
    Optional<User> user = userRepository.findByUsername(username);
    if (user.isPresent()) {
      return modelMapper.map(user.get(), ShortUserDetailsRs.class);
    }
    return null;
  }

  @Override
  public List<ShortUserDetailsRs> getUsersByUsername(String username) {
    List<User> users = userRepository.findByUsernameContainingIgnoreCase(username);
    if (users.isEmpty()) {
      return null;
    }

    List<ShortUserDetailsRs> currentUsers = users.stream()
        .map(user -> modelMapper.map(user, ShortUserDetailsRs.class))
        .collect(Collectors.toList());
    return currentUsers;
  }

  @Override
  public CurrentUserRs getCurrentUser() {
    UserPrincipal userPrincipal = UserPrincipal.getPrincipal();
    Optional<User> user = userRepository.findById(userPrincipal.getId());
    if (user.isPresent()) {
      return modelMapper.map(user.get(), CurrentUserRs.class);
    }

    throw new UsernameNotFoundException("User not found!");
  }

  @Override
  @Transactional
  public void createNewUser(UserRq userDtoNewUser) {
    User insertUser = modelMapper.map(userDtoNewUser, User.class);
    userRepository.save(insertUser);
  }

  @Override
  @Transactional
  public ResponseEntity<?> deleteUser(UserIdRq user) {
    UserPrincipal userPrincipal = UserPrincipal.getPrincipal();
    if (!user.getId().equals(userPrincipal.getId())) {
      return new ResponseEntity(new ApiRs(false, "Access denied"), HttpStatus.FORBIDDEN);
    }

    Optional<User> currentUser = userRepository.findById(user.getId());
    if (currentUser.isPresent()) {
      currentUser.get().setActive(false);
      userRepository.save(currentUser.get());
      return ResponseEntity.ok(new ApiRs(true, "Success"));
    }

    return new ResponseEntity(new ApiRs(false, "Bad request"), HttpStatus.BAD_REQUEST);
  }
}
