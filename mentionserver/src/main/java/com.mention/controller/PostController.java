package com.mention.controller;

import com.mention.model.Post;
import com.mention.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/post")
public class PostController {

  private PostService postService;

  @Autowired
  public PostController(PostService postService) {
    this.postService = postService;
  }

  @PostMapping
  public void addPost(@RequestBody Post post) { 
    postService.addPost(post);
  }

  @GetMapping(value = "/{id}")
  public Optional<Post> getPost(@PathVariable Long id) {
    return postService.getPost(id);
  }

  @PutMapping
  public void updatePost(@RequestBody Post post) {
    postService.updatePost(post);
  }

  @DeleteMapping(value = "/{id}")
  public void deletePost(@PathVariable Long id) {
    postService.deletePost(id);
  }
}
