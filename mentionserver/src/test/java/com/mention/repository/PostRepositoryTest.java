package com.mention.repository;

import com.mention.SpringBootConfiguration;
import com.mention.model.Post;
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
public class PostRepositoryTest {

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

  @Before
  public void before() {
    new UserBefore(userRepository).createNewUser(USER_NAME, USER_EMAIL, USER_PASSWD, USER_ACTIVE);
    new PostRepoBefore(postRepository).createNewPost(BODY, userRepository.findByUsername(USER_NAME).get());
  }

  @After
  public void after() {
    postRepository.deleteById(postRepository.findByAuthorUsername(USER_NAME).getId());
    Assert.assertNull(postRepository.findByAuthorUsername(USER_NAME));
    userRepository.deleteByUsername(USER_NAME);
    Assert.assertNull(userRepository.findByUsername(USER_NAME).orElse(null));
  }

  @Test
  public void savePostTest() {
    Assert.assertNotNull(postRepository.findByAuthorUsername(USER_NAME));
  }

  @Test
  public void getPostTest() {
    Assert.assertNotNull(postRepository.findByAuthorUsername(USER_NAME));
  }

 @Test
  public void updatePostTest() {
    Post updatePost = postRepository.findByAuthorUsername(USER_NAME);
    updatePost.setBody("It's honey");
    Long post_id = postRepository.save(updatePost).getId();
    Assert.assertNotEquals(updatePost, postRepository.findById(post_id));
  }

 @Test
  public void deletePostTest() {
   Assert.assertNotNull(postRepository.findByAuthorUsername(USER_NAME));
  }
}