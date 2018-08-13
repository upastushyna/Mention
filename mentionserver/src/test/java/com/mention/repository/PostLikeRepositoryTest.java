package com.mention.repository;

import com.mention.SpringBootConfiguration;
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
public class PostLikeRepositoryTest {

  private final static String USER_NAME = "username";
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

  @Before
  public void before() {
    new UserBefore(userRepository).createNewUser(USER_NAME, USER_EMAIL, USER_PASSWD, USER_ACTIVE);
    new PostRepoBefore(postRepository).createNewPost(BODY, userRepository.findByUsername(USER_NAME).get());
    new PostLikeRepoBefore(postLikeRepository).createNewPostLike(userRepository.findByUsername(USER_NAME).get(),
        postRepository.findByAuthorUsername(USER_NAME));

  }

  @After
  public void after() {
    postLikeRepository.deleteById(postLikeRepository.findByUserUsername(USER_NAME).getId());
    postRepository.deleteById(postRepository.findByAuthorUsername(USER_NAME).getId());
    userRepository.deleteByUsername(USER_NAME);
    Assert.assertNull(userRepository.findByUsername(USER_NAME).orElse(null));
  }

  @Test
  public void savePostLikeTest() {
    Assert.assertNotNull(postLikeRepository.findByUserUsername(USER_NAME));
  }

  @Test
  public void getPostLikeTest() {
    Assert.assertNotNull(postLikeRepository.findByUserUsername(USER_NAME));
  }


  @Test
  public void deletePostLikeTest() {
    Assert.assertNotNull(postLikeRepository.findByUserUsername(USER_NAME));
  }


}
