package com.mention.service;

import com.mention.dto.PostDtoIdRq;
import com.mention.dto.PostDtoRq;
import com.mention.dto.PostDtoRs;
import com.mention.model.Post;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface UserPostsService {

  List<PostDtoRs> getFollowedPosts(String username);

  List<PostDtoRs> getPostsByUsername(String username);

  List<PostDtoRs> getLikedPosts(String username);

  void addPost(String body, Long userId, MultipartFile file) throws IOException;

  void updatePost(PostDtoRq post);

  void deletePost(PostDtoIdRq post);

  void rePost(PostDtoRq post);

}
