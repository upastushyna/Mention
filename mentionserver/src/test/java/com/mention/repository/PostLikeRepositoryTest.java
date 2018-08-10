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

  /*public PostLikeRepositoryTest(PostLikeRepository postLikeRepository) {
    this.postLikeRepository = postLikeRepository;
  }*/

  @Before
  public void before() {
    new UserBefore(userRepository).createNewUser(USER_NAME, USER_EMAIL, USER_PASSWD, USER_ACTIVE);
 //   new UserBefore(userRepository).createNewUser(USER_NAME2, USER_EMAIL, USER_PASSWD, USER_ACTIVE);
    new PostRepoBefore(postRepository).createNewPost(BODY, userRepository.findByUsername(USER_NAME).get());
    new PostLikeRepoBefore(postLikeRepository).createNewPostLike(userRepository.findByUsername(USER_NAME).get(), postRepository.findByAuthor_Username(USER_NAME));

  }

  @After
  public void after() {
    postRepository.deleteById(postRepository.findByAuthor_Username(USER_NAME).getId());
    Assert.assertNull(postRepository.findByAuthor_Username(USER_NAME));
    userRepository.deleteByUsername(USER_NAME);
    userRepository.deleteByUsername(USER_NAME2);
    Assert.assertNull(userRepository.findByUsername(USER_NAME).orElse(null));
    Assert.assertNull(userRepository.findByUsername(USER_NAME2).orElse(null));
  }

  @Test
  public void savePostLikeTest() {
    Assert.assertNotNull(postLikeRepository.findByUserUsername(USER_NAME));
  }

  @Test
  public void getPostLikeTest() {
    Assert.assertNotNull(postRepository.findByAuthor_Username(USER_NAME));
  }

  @Test
  public void updatePostLikeTest() {

  }

  @Test
  public void deletePostLikeTest() {

  }


}
