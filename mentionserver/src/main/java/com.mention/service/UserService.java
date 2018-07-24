package com.mention.service;

import com.mention.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface UserService {

  void addUser(User user);

  Optional<User> getUser(Long id);

  void updateUser(User user);

  void deleteUser(Long id);
}
