package com.mention.controller;

import com.mention.dto.CommentLikeDtoRq;
import com.mention.service.CommentLikeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/commentlikes")
public class UserCommentLikeController {
  private CommentLikeServiceImpl commentLikeService;

  @Autowired
  public UserCommentLikeController(CommentLikeServiceImpl commentLikeService) {
    this.commentLikeService = commentLikeService;
  }

  @PostMapping("/add")
  public void addCommentLike(@RequestBody CommentLikeDtoRq commentLikeDto) {
    commentLikeService.addCommentLike(commentLikeDto);
  }

  @DeleteMapping("/delete")
  public void deleteCommentLike(@RequestBody CommentLikeDtoRq commentLikeDto) {
    commentLikeService.deleteCommentLike(commentLikeDto);
  }
}
