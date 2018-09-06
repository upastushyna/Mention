package com.mention.service;

import com.mention.dto.PostIdRq;
import com.mention.dto.PostRq;
import com.mention.dto.PostRs;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface PostService {

  List<PostRs> getFollowedPosts(String username, int page, int size);

  List<PostRs> getPostsByUsername(String username);

  List<PostRs> getLikedPosts(String username);

  List<PostRs> getPostsByBody(String body);

  ResponseEntity<?> addPost(String body, Long userId, MultipartFile file) throws IOException;

  ResponseEntity<?> deletePost(PostIdRq post);

  ResponseEntity<?> rePost(PostRq post);




}
