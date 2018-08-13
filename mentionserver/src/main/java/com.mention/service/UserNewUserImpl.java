package com.mention.service;

import com.mention.dto.UserDtoIdRq;
import com.mention.dto.UserDtoNewUserRq;
import com.mention.model.User;
import com.mention.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UserNewUserImpl implements UserNewUser {

  private UserRepository userRepository;

  @Autowired
  public UserNewUserImpl(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  @Transactional
  public void createNewUser(UserDtoNewUserRq userDtoNewUser) {
    ModelMapper modelMapper = new ModelMapper();
    User insertUser = modelMapper.map(userDtoNewUser, User.class);
    insertUser.setActive(true);
    userRepository.save(insertUser);
  }

  @Override
  @Transactional
  public void deleteUser(UserDtoIdRq user) {
    ModelMapper modelMapper = new ModelMapper();
    Optional<User> currentUser = userRepository.findById(user.getId());
    if (currentUser.isPresent() && currentUser.get().isActive()) {
      currentUser.get().setActive(false);
      userRepository.save(currentUser.get());
    }
  }
}
