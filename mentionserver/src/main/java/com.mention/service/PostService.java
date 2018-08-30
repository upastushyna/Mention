package com.mention.service;

import com.mention.dto.PostIdRq;
import com.mention.dto.PostRq;
import com.mention.dto.PostRs;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface PostService {

  List<PostRs> getFollowedPosts(String username);

  List<PostRs> getPostsByUsername(String username);

  List<PostRs> getLikedPosts(String username);

  List<PostRs> getPostsByBody(String body);

  void addPost(String body, Long userId, MultipartFile file) throws IOException;

  void updatePost(PostRq post);

  void deletePost(Long id);

  void rePost(PostRq post);

}
