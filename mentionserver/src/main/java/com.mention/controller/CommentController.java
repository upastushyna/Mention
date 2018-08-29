package com.mention.controller;

import com.mention.dto.CommentRq;
import com.mention.service.CommentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/comments")
public class CommentController {

  private CommentServiceImpl userCommentsService;

  @Autowired
  public CommentController(CommentServiceImpl userCommentsService) {
    this.userCommentsService = userCommentsService;
  }

  @PostMapping("/add")
  public void addComment(@Valid @RequestBody CommentRq comment) {
    userCommentsService.addComment(comment);
  }
}