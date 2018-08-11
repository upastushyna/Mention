package com.mention.service;

import com.mention.dto.PostDtoRq;
import com.mention.dto.PostDtoRs;

import java.util.List;

public interface UserPostsService {

  List<PostDtoRs> getFollowedPosts(String username);

  List<PostDtoRs> getPostsByUsername(String username);

  void addPost(PostDtoRq post);

  void updatePost(PostDtoRq post);

}
