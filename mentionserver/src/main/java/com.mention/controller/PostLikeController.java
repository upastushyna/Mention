package com.mention.controller;

import com.mention.dto.PostLikeRq;
import com.mention.service.PostLikeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/postlikes")
public class PostLikeController {
  private PostLikeServiceImpl postLikeService;

  @Autowired
  public PostLikeController(PostLikeServiceImpl postLikeService) {
    this.postLikeService = postLikeService;
  }

  @PostMapping("/add")
  public ResponseEntity<?> addPostLike(@RequestBody PostLikeRq postLike) {
    return postLikeService.addPostLike(postLike);
  }

  @DeleteMapping("/delete")
  public ResponseEntity<?> deletePostLike(@RequestBody PostLikeRq postLike) {
    return postLikeService.deletePostLike(postLike);
  }
}
