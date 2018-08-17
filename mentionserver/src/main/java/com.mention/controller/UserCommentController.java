package com.mention.controller;

import com.mention.dto.CommentDtoRq;
import com.mention.service.UserCommentsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/comments")
public class UserCommentController {

  private UserCommentsServiceImpl userCommentsService;

  @Autowired
  public UserCommentController(UserCommentsServiceImpl userCommentsService) {
    this.userCommentsService = userCommentsService;
  }

  @PostMapping("/add")
  public void addComment(@Valid @RequestBody CommentDtoRq comment) {
    userCommentsService.addComment(comment);
  }
}