package com.mention.repository;

import com.mention.model.Post;
import com.mention.model.PostLike;
import com.mention.model.User;
import org.junit.Assert;

public class PostLikeRepoBefore {
  private PostLikeRepository postLikeRepository;
  private UserRepository userRepository;

  public PostLikeRepoBefore(PostLikeRepository postLikeRepository) {
    this.postLikeRepository = postLikeRepository;
  }

  public PostLikeRepoBefore(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public void createNewPostLike(User author, Post post) {
    Assert.assertNull(postLikeRepository.findByUser(author.getUsername()));
    PostLike postLike = new PostLike(author, post);
    postLikeRepository.save(postLike);
    Assert.assertNotNull(postLikeRepository.findByUser(author.getUsername()));
  }
}
