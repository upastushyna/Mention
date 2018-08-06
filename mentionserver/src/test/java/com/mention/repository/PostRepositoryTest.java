package com.mention.repository;

import com.mention.SpringBootConfiguration;
import com.mention.model.Post;
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
public abstract class PostRepositoryTest {

  private final static String USER_NAME = "username";
  private final static String USER_EMAIL = "john@mail.com";
  private final static String USER_PASSWD = "123";
  private final static boolean USER_ACTIVE = true;
  private final static String BODY = "It's sweet all like honey";

  @Autowired
  private PostRepository postRepository;

  @Autowired
  private UserRepository userRepository;

 /* @BeforeClass
  public void beforeClass() {
    new UserBefore(userRepository).createNewUser(USER_NAME, USER_EMAIL, USER_PASSWD, USER_ACTIVE);
  }

  @AfterClass
  public void afterClass() {
    userRepository.deleteByUsername(USER_NAME);
    Assert.assertNull(userRepository.findByUsername(USER_NAME).orElse(null));
  }*/

  @Before
  public void before() {
    new UserBefore(userRepository).createNewUser(USER_NAME, USER_EMAIL, USER_PASSWD, USER_ACTIVE);
    new PostRepoBefore(postRepository).createNewPost(BODY, userRepository.findByUsername(USER_NAME).get());
  }

  @After
  public void after() {
    postRepository.deleteById(postRepository.findByAuthor(userRepository.findByUsername(USER_NAME).get()).getId());
    Assert.assertNull(postRepository.findByAuthor(userRepository.findByUsername(USER_NAME).get()));
    userRepository.deleteByUsername(USER_NAME);
    Assert.assertNull(userRepository.findByUsername(USER_NAME).orElse(null));
  }

  @Test
  public void savePostTest() {
    Assert.assertNotNull(postRepository.findByAuthor(userRepository.findByUsername(USER_NAME).get()));
  }

  @Test
  public void getPostTest() {
    Assert.assertNotNull(postRepository.findByAuthor(userRepository.findByUsername(USER_NAME).get()));
  }

 @Test
  public void updatePostTest() {
    Post updatePost = postRepository.findByAuthor(userRepository.findByUsername(USER_NAME).get());
    updatePost.setBody("It's honey");
    postRepository.save(updatePost);
    Assert.assertNotEquals(updatePost, postRepository.findByAuthor(userRepository.findByUsername(USER_NAME).get()));
    postRepository.deleteById(postRepository.findByAuthor(userRepository.findByUsername(USER_NAME).get()).getId());
    Assert.assertNull(postRepository.findByAuthor(userRepository.findByUsername(USER_NAME).get()));
  }

 @Test
  public void deletePostTest() {
   postRepository.deleteById(postRepository.findByAuthor(userRepository.findByUsername(USER_NAME).get()).getId());
   Assert.assertNull(postRepository.findByAuthor(userRepository.findByUsername(USER_NAME).get()));
 }
}