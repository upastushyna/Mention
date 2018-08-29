package com.mention.controller;

import com.mention.dto.PostDtoIdRq;
import com.mention.dto.PostDtoRq;
import com.mention.dto.PostDtoRs;
import com.mention.dto.UserDtoIdRq;
import com.mention.dto.UserDtoRq;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
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

  @GetMapping("/liked/{username}")
  public List<PostDtoRs> getLikedPosts(@PathVariable String username) {
    return userPostsService.getLikedPosts(username);
  }

  @GetMapping("/search/{body}")
  public List<PostDtoRs> getPostsByBody(@PathVariable String body) {
    return userPostsService.getPostsByBody(body.replace("%20", " "));
  }

  @PostMapping("/add")
  public void addPost(@RequestParam("body") String body,
                      @RequestParam("id") Long id,
                      @RequestParam(value = "image", required = false) MultipartFile file)
      throws IOException {
    if (body.length() > 0 && body.length() <= 280) {
      userPostsService.addPost(body, id, file);
    }
  }

  @PostMapping("/repost")
  public void rePost(@RequestBody PostDtoRq post) {
    userPostsService.rePost(post);
  }

  @PutMapping("/update")
  public void updatePost(@Valid @RequestBody PostDtoRq post) {
    userPostsService.updatePost(post);
  }

  @DeleteMapping("/delete")
  public void deletePost(@RequestBody PostDtoIdRq post) {
    userPostsService.deletePost(post);
  }

/*  @DeleteMapping("/delete/{postId}")
  public void deletePost(@PathVariable Long postId) {
    userPostsService.deletePost(postId);
  }*/
}
