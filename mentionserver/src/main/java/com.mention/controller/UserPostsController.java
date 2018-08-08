package com.mention.controller;


import com.mention.dto.PostDtoRs;
import com.mention.service.UserPostsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserPostsController {

  private UserPostsServiceImpl userPostsService;

  @Autowired
  public UserPostsController(UserPostsServiceImpl userPostsService) {
    this.userPostsService = userPostsService;
  }

  @GetMapping("/followed/{username}")
  public List<PostDtoRs> getFollowedPosts(@PathVariable String username) {
    return userPostsService.getFollowedPosts(username);
  }

  @GetMapping("/posts/{username}")
  public List<PostDtoRs> getPostsByUsername(@PathVariable String username) {
    return userPostsService.getPostsByUsername(username);
  }

}
