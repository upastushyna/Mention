package com.mention.service002;

import com.mention.model.User;

import java.util.Optional;

public interface UserService {

  void addUser(User user);

  Optional<User> getUser(Long id);

  void updateUser(User user);

  void deleteUser(Long id);
}
