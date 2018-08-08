package com.mention.repository;

import com.mention.SpringBootConfiguration;
import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
@SpringBootTest(classes = SpringBootConfiguration.class)
public class PostLikeRepositoryTest {

  private final static String USER_NAME = "username";
  private final static String USER_NAME2 = "username2";
  private final static String USER_EMAIL = "john@mail.com";
  private final static String USER_PASSWD = "123";
  private final static boolean USER_ACTIVE = true;
  private final static String BODY = "It's sweet all like honey";

  @Autowired
  private PostRepository postRepository;

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private PostLikeRepository postLikeRepository;

  public PostLikeRepositoryTest(PostLikeRepository postLikeRepository) {
    this.postLikeRepository = postLikeRepository;
  }


  @Before
  public void before() {

  }

  @After
  public void after() {

  }

}
