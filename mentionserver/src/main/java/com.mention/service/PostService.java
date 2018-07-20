package com.mention.service;

import com.mention.model.Post;
import org.springframework.stereotype.Service;

@Service
public interface PostService {

  void addPost(Post post);

  Post getPost(Long post_id);

  void updatePost(Post post);

  void deletePost(Long post_id);
}
