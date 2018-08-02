package com.mention.repository;

import com.mention.SpringBootConfiguration;
import com.mention.model.User;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import org.junit.Rule;

@RunWith(SpringRunner.class)
//@RunWith(SpringJUnit4ClassRunner.class)
@DataJpaTest
@SpringBootTest(classes = SpringBootConfiguration.class)
public class UserRepositoryTest {

  @Autowired
  private UserRepository userRepository;

  @Before
  public void before(){
    UserBefore.createNewUser(userRepository);
  }

  @After
  public void after(){
    userRepository.deleteByUsername("username");
    Assert.assertNull(userRepository.findByUsername("username"));
  }

  @Test
  public void getUserTest() {
    Assert.assertNotNull(userRepository.findUserByEmail("john@mail.com"));
  }

  @Test
  public void saveUserTest() {
    Assert.assertNotNull(userRepository.findByUsername("username"));
  }

  @Test
  public void updateUserTest() {
    User updateUser = userRepository.findByUsername("username");
    updateUser.setUsername("Jack");
    userRepository.save(updateUser);
    Assert.assertEquals(updateUser, userRepository.findByUsername("Jack"));
    userRepository.deleteByUsername("Jack");
    Assert.assertNull(userRepository.findByUsername("Jack"));
   }

  @Test
  public void deleteUserTest() {
    Assert.assertNotNull(userRepository.findByUsername("username"));
    userRepository.deleteByUsername("username");
    Assert.assertNull(userRepository.findByUsername("username"));
  }

}
