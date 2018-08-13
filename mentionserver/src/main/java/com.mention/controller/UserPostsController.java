package com.mention.controller;

import com.mention.dto.PostDtoRq;
import com.mention.dto.PostDtoRs;
import com.mention.service.UserPostsServiceImpl;
import org.aspectj.lang.annotation.DeclareError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
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

  @GetMapping("/{username}")
  public List<PostDtoRs> getPostsByUsername(@PathVariable String username) {
    return userPostsService.getPostsByUsername(username);
  }

  @PostMapping("/add")
  public void addPost(@RequestBody PostDtoRq post) {
    userPostsService.addPost(post);
  }

  @PutMapping("/update")
  public void updatePost(@RequestBody PostDtoRq post) {
    userPostsService.updatePost(post);
  }

  @DeleteMapping(value = "/{id}")
  public void deletePost(@PathVariable Long id) {
    userPostsService.deletePostById(id);
  }
}
