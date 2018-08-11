package com.mention.controller;

import com.mention.dto.PostLikeRq;
import com.mention.service.PostLikeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/postlikes")
public class UserPostLikesController {
  private PostLikeServiceImpl postLikeService;

  @Autowired
  public UserPostLikesController(PostLikeServiceImpl postLikeService) {
    this.postLikeService = postLikeService;
  }

  @PostMapping("/add")
  public void addPostLike(@RequestBody PostLikeRq postLike) {
    postLikeService.addPostLike(postLike);
  }

  @PostMapping("/delete")
  public void deletePostLike(@RequestBody PostLikeRq postLike) {
    postLikeService.deletePostLike(postLike);
  }
}
