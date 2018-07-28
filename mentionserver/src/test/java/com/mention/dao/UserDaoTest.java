package com.mention.dao;

import com.mention.model.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UserDaoTest {

  @Autowired
  private TestEntityManager entityManager;

  @Autowired
  private UserDao userDao;

/*  @Autowired
  public TestEntityManager entityManager(UserDao userDao) {
    this.userDao = userDao;
  }*/

  User Petya = new User("petrio", "m@m.c", "123");

  private User john;

  public void UserDaoTest(){
    //given user
   john = new User();
   john.setUsername("johnny");
   john.setEmail("jo@mail.com");
   john.setPassword("213443");
  }

  //Set up here
  @Before
  public void setUp() {
  entityManager.persist(john);
  entityManager.flush();
  }

  //write test cases here

  @Test
  public void mustFindByUsername() {

    //when found
    User found = userDao.findByUsername(john.getUsername());

    //then check login field
    assertThat(found.getUsername()).isEqualTo(john.getUsername());
  }

  @Test
  public void mustfindByEmail() {

    //when found
    User found = userDao.findUserByEmail(john.getEmail());

    //then check

    assertThat(found.getEmail()).isEqualTo(john.getEmail());
  }

}
