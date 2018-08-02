package com.mention.repository;

import com.mention.model.User;
import org.junit.Assert;

public class UserBefore {
  private final UserRepository userRepository;

  public UserBefore(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public void createNewUser(String name, String passwd, String email, boolean userActive) {
    Assert.assertNull(userRepository.findByUsername(name));
    User user = new User();
    user.setUsername(name);
    user.setActive(userActive);
    user.setEmail(email);
    user.setPassword(passwd);
    userRepository.save(user);
    Assert.assertNotNull(userRepository.findByUsername(name));
  }
}
