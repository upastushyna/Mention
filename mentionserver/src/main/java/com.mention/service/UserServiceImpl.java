package com.mention.service;

import com.mention.dto.CurrentUserDtoRs;
import com.mention.dto.ShortUserDetailsRs;
import com.mention.dto.UserDtoIdRq;
import com.mention.dto.UserDtoRq;
import com.mention.model.User;
import com.mention.repository.UserRepository;
import com.mention.security.UserPrincipal;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
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
    if (!users.isEmpty()) {
      List<ShortUserDetailsRs> currentUsers = users.stream()
          .map(user -> modelMapper.map(user, ShortUserDetailsRs.class))
          .collect(Collectors.toList());
      return currentUsers;
    }
    return null;
  }

  @Override
  public CurrentUserDtoRs getCurrentUser() {
    UserPrincipal userPrincipal = (UserPrincipal) SecurityContextHolder
        .getContext()
        .getAuthentication()
        .getPrincipal();
    Optional<User> user = userRepository.findById(userPrincipal.getId());
    if (user.isPresent()) {
      return modelMapper.map(user.get(), CurrentUserDtoRs.class);
    }
    throw new UsernameNotFoundException("User not found!");
  }

  @Override
  public ShortUserDetailsRs getUser(Long id) {
    Optional<User> user = userRepository.findById(id);
    if (user.isPresent()) {
      return modelMapper.map(user.get(), ShortUserDetailsRs.class);
    }
    return null;
  }

  @Override
  @Transactional
  public void createNewUser(UserDtoRq userDtoNewUser) {
    User insertUser = modelMapper.map(userDtoNewUser, User.class);
    userRepository.save(insertUser);
  }

  @Override
  @Transactional
  public void deleteUser(UserDtoIdRq user) {
    Optional<User> currentUser = userRepository.findById(user.getId());
    if (currentUser.isPresent()) {
      currentUser.get().setActive(false);
      userRepository.save(currentUser.get());
    }
  }
}
