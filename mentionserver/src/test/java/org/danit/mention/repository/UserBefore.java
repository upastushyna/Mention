package org.danit.mention.repository;

import org.danit.mention.model.User;
import org.junit.Assert;

public class UserBefore {
  private UserRepository userRepository;

  public UserBefore(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public void createNewUser(String name, String email, String passwd, boolean userActive) {
    Assert.assertNull(userRepository.findByUsername(name).orElse(null));
    User user = new User(name, email, passwd, userActive);
    userRepository.save(user);
    Assert.assertNotNull(userRepository.findByUsername(name).orElse(null));
  }
}
