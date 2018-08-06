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
    Assert.assertNull(postRepository.findByAuthor(author));
    Post post = new Post(body, author);
    postRepository.save(post);
    Assert.assertNotNull(postRepository.findByAuthor(author));
  }
}
