package com.mention.service;


import com.mention.dto.CommentLikeDtoRq;

public interface CommentLikeService {

  void addCommentLike(CommentLikeDtoRq commentLikeDto);

  void deleteCommentLike(CommentLikeDtoRq commentLikeDto);

}
