package com.mention.controller;

import com.mention.dto.PostIdRq;
import com.mention.dto.PostRq;
import com.mention.dto.PostRs;
import com.mention.service.PostServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {

  private PostServiceImpl userPostsService;

  @Autowired
  public PostController(PostServiceImpl userPostsService) {
    this.userPostsService = userPostsService;
  }

  @GetMapping("/followed/{username}")
  public List<PostRs> getFollowedPosts(@PathVariable String username) {
    return userPostsService.getFollowedPosts(username);
  }

  @GetMapping("/{username}")
  public List<PostRs> getPostsByUsername(@PathVariable String username) {
    return userPostsService.getPostsByUsername(username);
  }

  @GetMapping("/liked/{username}")
  public List<PostRs> getLikedPosts(@PathVariable String username) {
    return userPostsService.getLikedPosts(username);
  }

  @GetMapping("/search/{body}")
  public List<PostRs> getPostsByBody(@PathVariable String body) {
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
  public void rePost(@RequestBody PostRq post) {
    userPostsService.rePost(post);
  }

  @PutMapping("/update")
  public void updatePost(@Valid @RequestBody PostRq post) {
    userPostsService.updatePost(post);
  }

  @DeleteMapping("/delete")
  public void deletePost(@RequestBody PostIdRq post) {
    userPostsService.deletePost(post);
  }
}
