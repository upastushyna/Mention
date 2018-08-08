package com.mention.service002;

import com.mention.model.Post;

import java.util.Optional;

public interface PostService {

  void addPost(Post post);

  Optional<Post> getPost(Long id);

  void updatePost(Post post);

  void deletePost(Long id);
}
