package com.mention.controller;

import com.mention.dto.CommentLikeRq;
import com.mention.service.CommentLikeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/commentlikes")
public class CommentLikeController {
  private CommentLikeServiceImpl commentLikeService;

  @Autowired
  public CommentLikeController(CommentLikeServiceImpl commentLikeService) {
    this.commentLikeService = commentLikeService;
  }

  @PostMapping("/add")
  public void addCommentLike(@Valid @RequestBody CommentLikeRq commentLikeDto) {
    commentLikeService.addCommentLike(commentLikeDto);
  }

  @DeleteMapping("/delete")
  public void deleteCommentLike(@RequestBody CommentLikeRq commentLikeDto) {
    commentLikeService.deleteCommentLike(commentLikeDto);
  }
}
