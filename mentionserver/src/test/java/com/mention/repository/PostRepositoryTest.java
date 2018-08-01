/*


*/
/*
public class PostRepositoryTest {
}
*//*

package com.mention.repository;

import com.mention.SpringBootConfiguration;
import com.mention.model.Post;
import com.mention.model.User;
import com.mention.repository.UserRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringBootConfiguration.class)
public class PostRepositoryTest {

  @Autowired
  private PostRepository postRepository;

  @Autowired
  private UserRepository userRepository;

  public User createTestUser(){
    User author = new User();
    author.setUsername("John  Dou");
    author.setActive(true);
    author.setEmail("john@mail.com");
    author.setPassword("123");
    userRepository.save(author);
    return author;
  }


  @Test
  public void findTest() {
    String author = "assd";
    Assert.assertNull(postRepository.findByAuthor(author));
  }

  @Test
  public void saveTest() {
    createTestUser();
    Post post = new Post();
    post.setAuthor("author");
    post.setBody("Its nice to see test body");
    postRepository.save(post);
    Assert.assertNotNull(userRepository.findByUsername("username"));
  }

  @Test
  public void updateTest() {
    User user = new User();
    user.setUsername("sully");
    user.setActive(true);
    user.setPassword("1234");
    user.setEmail("rick@gmail.com");
    userRepository.save(user);
    User user2 = userRepository.findByUsername("sully");
    user2.setUsername("kolya");
    userRepository.save(user2);
    Assert.assertNull(userRepository.findByUsername("sully"));
    Assert.assertNotNull(userRepository.findByUsername("kolya"));
  }

  @Test
  @Transactional
  public void deleteTest() {
    User user = new User();
    user.setUsername("bobby");
    user.setActive(true);
    user.setPassword("1234");
    user.setEmail("jake@gmail.com");
    userRepository.save(user);
    Assert.assertNotNull(userRepository.findByUsername("bobby"));
    userRepository.deleteByUsername("bobby");
    Assert.assertNull(userRepository.findByUsername("bobby"));

  }
}*/
