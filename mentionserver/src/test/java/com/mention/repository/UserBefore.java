package com.mention.repository;

import com.mention.model.User;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;

public class UserBefore {
  private UserRepository userRepository;

  public UserBefore(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public void createNewUser(String name, String passwd, String email, boolean userActive) {
    Assert.assertNull(userRepository.findByUsername(name).orElse(null));
    User user = new User(name, email, passwd, userActive);
    userRepository.save(user);
    Assert.assertNotNull(userRepository.findByUsername(name).orElse(null));
  }
}
