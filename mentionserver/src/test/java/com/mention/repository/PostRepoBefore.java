package com.mention.repository;


import com.mention.model.Post;
import com.mention.model.User;
import org.junit.Assert;

import java.util.Date;

public class PostRepoBefore {
  private PostRepository postRepository;

  private UserRepository userRepository;



  public PostRepoBefore(PostRepository postRepository) {
    this.postRepository = postRepository;
  }

  public void createNewPost(String body, User author) {
    Assert.assertNull(postRepository.findByAuthor_Username(author.getUsername()));
    Post post = new Post(body, author);
    Long before_post_id = postRepository.save(post).getId();
    Assert.assertNotNull(postRepository.findByAuthor_Username(author.getUsername()));
  }
}