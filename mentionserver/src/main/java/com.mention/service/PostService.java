package com.mention.service;

import com.mention.model.Post;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface PostService {

  void addPost(Post post);

  Optional<Post> getPost(Long id);

  void updatePost(Post post);

  void deletePost(Long id);
}
