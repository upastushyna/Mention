package com.mention.service;

import com.mention.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

  void addUser(User user);

  User getUser(Long id);

  void updateUser(User user);

  void deleteUser(Long id);
}
