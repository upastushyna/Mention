/*
package com.mention.repository;

import User;
import org.junit.Assert;
import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;
import org.springframework.beans.factory.annotation.Autowired;

public class UserRepoRule implements TestRule {

  private final UserRepository userRepository;

  public UserRepoRule(UserRepository userRepository) {
    this.userRepository = userRepository;
  }


  @Override
  public Statement apply(final Statement base, Description description) {
    return new Statement() {
      @Override
      public void evaluate() throws Throwable {
        base.evaluate();
        userRepository.deleteByUsername("username");
        Assert.assertNull(userRepository.findByUsername("username"));
      }
    };
  }
}
*/
