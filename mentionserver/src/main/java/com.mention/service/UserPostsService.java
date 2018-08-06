package com.mention.service;

import com.mention.dto.PostDto;

import java.util.List;

public interface UserPostsService {

  List<PostDto> getFollowedPosts(String username);
}
