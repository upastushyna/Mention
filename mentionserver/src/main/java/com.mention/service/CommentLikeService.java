package com.mention.service;


import com.mention.dto.CommentLikeRq;
import org.springframework.http.ResponseEntity;

public interface CommentLikeService {

  ResponseEntity<?> addCommentLike(CommentLikeRq commentLikeDto);

  ResponseEntity<?> deleteCommentLike(CommentLikeRq commentLikeDto);

}
