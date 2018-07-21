package com.mention.controller;

import com.mention.model.Post;
import com.mention.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/post")
public class PostController {

  @Autowired
    PostService postService;

  @PostMapping
  @Transactional
  public void addPost(@RequestBody Post post) { 
    postService.addPost(post);
  }

  @GetMapping(value = "/{post_id}")
  public Post getPost(@PathVariable Long post_id) { 
    return postService.getPost(post_id);
  }

  @PutMapping(value = "/{post_id}")
  @Transactional
  public void updatePost(@PathVariable Long post_id, @RequestBody Post post) {
    postService.updatePost(post);
  }

  @DeleteMapping(value = "/{post_id}")
  @Transactional
  public void deletePost(@PathVariable Long post_id) {
    postService.deletePost(post_id);
  }
}
