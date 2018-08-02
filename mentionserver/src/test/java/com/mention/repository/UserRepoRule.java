package com.mention.repository;

import com.mention.model.User;
import org.junit.Assert;
import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;
import org.springframework.beans.factory.annotation.Autowired;

public class UserRepoRule implements TestRule {

  @Autowired
  private UserRepository userRepository;

  @Override
  public Statement apply(final Statement base, Description description) {
    return new Statement() {
      @Override
      public void evaluate() throws Throwable {
/*        Assert.assertNull(userRepository.findByUsername("username"));
        User user = new User();
        user.setUsername("username");
        user.setActive(true);
        user.setEmail("john@mail.com");
        user.setPassword("123");
        userRepository.save(user);
        Assert.assertNotNull(userRepository.findByUsername("username"));
        base.evaluate();
        userRepository.deleteByUsername("username");
        Assert.assertNull(userRepository.findByUsername("username"));*/
      }
    };
  }
}
