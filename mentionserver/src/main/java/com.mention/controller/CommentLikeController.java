package com.mention.controller;

import com.mention.dto.CommentLikeRq;
import com.mention.service.CommentLikeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
  public ResponseEntity<?> addCommentLike(@Valid @RequestBody CommentLikeRq commentLikeDto) {
    return commentLikeService.addCommentLike(commentLikeDto);
  }

  @DeleteMapping("/delete")
  public ResponseEntity<?> deleteCommentLike(@RequestBody CommentLikeRq commentLikeDto) {
    return commentLikeService.deleteCommentLike(commentLikeDto);
  }
}
