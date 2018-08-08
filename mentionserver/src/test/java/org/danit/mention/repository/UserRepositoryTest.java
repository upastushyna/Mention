package org.danit.mention.repository;

import org.danit.mention.SpringBootConfiguration;
import org.danit.mention.model.User;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@DataJpaTest
@SpringBootTest(classes = SpringBootConfiguration.class)
public class UserRepositoryTest {

  private final static String USER_NAME = "username";
  private final static String USER_NAME2 = "Jack";
  private final static String USER_EMAIL = "john@mail.com";
  private final static String USER_PASSWD = "123";
  private final static boolean USER_ACTIVE = true;

  @Autowired
  private UserRepository userRepository;

  @Before
  public void before() {
    new UserBefore(userRepository).createNewUser(USER_NAME, USER_EMAIL, USER_PASSWD, USER_ACTIVE);
  }

  @After
  public void after() {
    userRepository.deleteByUsername(USER_NAME);
    Assert.assertNull(userRepository.findByUsername(USER_NAME).orElse(null));
  }

  @Test
  public void getUserTest() {
    Assert.assertNotNull(userRepository.findByUsername(USER_NAME));
  }

  @Test
  public void getUserByMailTest() {
    Assert.assertNotNull(userRepository.findUserByEmail("john@mail.com"));
  }

  @Test
  public void saveUserTest() {
    Assert.assertNotNull(userRepository.findByUsername(USER_NAME));
  }

  @Test
  public void updateUserTest() {
    User updateUser = userRepository.findByUsername(USER_NAME).get();
    updateUser.setUsername("Jack");
    userRepository.save(updateUser);
    Assert.assertEquals(updateUser, userRepository.findByUsername(USER_NAME2).get());
    userRepository.deleteByUsername(USER_NAME2);
    Assert.assertNull(userRepository.findByUsername(USER_NAME2).orElse(null));
   }

  @Test
  public void deleteUserTest() {
    Assert.assertNotNull(userRepository.findByUsername(USER_NAME));
    userRepository.deleteByUsername(USER_NAME);
    Assert.assertNull(userRepository.findByUsername(USER_NAME).orElse(null));
  }

}
