package com.mention.controller;

import com.mention.model.Comment;
import com.mention.service002.CommentService;
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
@RequestMapping("/comment")
public class CommentController {
  private CommentService commentService;

  @Autowired
  public CommentController(CommentService commentService) {
    this.commentService = commentService;
  }

  @PostMapping
  public void addComment(@RequestBody Comment comment) {
    commentService.addComment(comment);
  }

  @PutMapping
  public void updateComment(@RequestBody Comment comment) {
    commentService.updateComment(comment);
  }

  @DeleteMapping(value = "/{id}")
  public void deleteComment(@PathVariable Long id) {
    commentService.deleteComment(id);
  }

  @GetMapping(value = "/{id}")
  public Optional<Comment> getComment(@PathVariable Long id) {
    return commentService.getComment(id);
  }
}
