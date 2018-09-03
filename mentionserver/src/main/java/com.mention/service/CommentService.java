package com.mention.service;

import com.mention.dto.CommentIdRq;
import com.mention.dto.CommentRq;
import org.springframework.http.ResponseEntity;

public interface CommentService {

  ResponseEntity<?> addComment(CommentRq comment);

  ResponseEntity<?> deleteComment(CommentIdRq comment);
}
