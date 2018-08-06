

/*
public class PostRepositoryTest {
}
*/
package com.mention.repository;

import com.mention.SpringBootConfiguration;
import com.mention.model.Post;
import com.mention.model.User;
import com.mention.repository.UserRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringBootConfiguration.class)
public abstract class PostRepositoryTest {

  @Autowired
  private PostRepository postRepository;

  @Autowired
  private UserRepository userRepository;


}