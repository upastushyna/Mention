package com.mention.repository;

import com.mention.SpringBootConfiguration;
import com.mention.model.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import org.junit.Rule;

@RunWith(SpringRunner.class)
@DataJpaTest
@SpringBootTest(classes = SpringBootConfiguration.class)
public class UserRepositoryTest {

  @Autowired
  private UserRepository userRepository;

/*
  @Rule
  public UserRepoRule rule = new UserRepoRule();
*/


  @Before
  public void before() {
    User user = new User();
    user.setUsername("username");
    user.setActive(true);
    user.setEmail("john@mail.com");
    user.setPassword("123");
    userRepository.save(user);
  }

  @Test
  public void findTest() {
/*    String username = "username";*/
    Assert.assertNull(userRepository.findByUsername("username"));
  }

  @Test
  public void saveUserTest() {
    Assert.assertNotNull(userRepository.findByUsername("username"));

  }

  @Test
  public void updateUserTest() {
/*
    User updUser = new User();
    updUser.setUsername("Joe Dou");
    updUser.setPassword("321");
    updUser.setActive(true);
    updUser.setEmail("joe@2mail.com");
    userRepository.save(updUser);
*/

    User updUser2 = userRepository.findByUsername("username");
    updUser2.setUsername("Jack");
    userRepository.save(updUser2);
    Assert.assertNotEquals(updUser2, userRepository.findByUsername("username"));
   // Assert.assertNotNull(userRepository.findByUsername(updUser2.getUsername()));
  }

  @Test
  @Transactional
  public void deleteUserTest() {
    User userDel = new User();
    userDel.setUsername("Elly");
    userDel.setActive(true);
    userDel.setPassword("231");
    userDel.setEmail("elly@ma.ma");
    userRepository.save(userDel);
    Assert.assertNotNull(userRepository.findByUsername("Elly"));
    userRepository.deleteByUsername("Elly");
    Assert.assertNull(userRepository.findByUsername("Elly"));
  }

}
