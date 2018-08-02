package com.mention.repository;

import com.mention.model.User;
import org.junit.Assert;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;


public class UserBefore {

  @Autowired
  private UserRepository userRepository;

  public void createNewUser() {
    Assert.assertNull(userRepository.findByUsername("username"));
    User user = new User();
    user.setUsername("username");
    user.setActive(true);
    user.setEmail("john@mail.com");
    user.setPassword("123");
    userRepository.save(user);
    Assert.assertNotNull(userRepository.findByUsername("username"));
  }
}
