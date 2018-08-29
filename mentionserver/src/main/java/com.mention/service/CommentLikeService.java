package com.mention.service;


import com.mention.dto.CommentLikeRq;

public interface CommentLikeService {

  void addCommentLike(CommentLikeRq commentLikeDto);

  void deleteCommentLike(CommentLikeRq commentLikeDto);

}
