package com.mention.service;

import com.mention.model.Comment;

import java.util.Optional;

public interface CommentService {

  void addComment(Comment comment);

  void updateComment(Comment comment);

  void deleteComment(Long id);

  Optional<Comment> getComment(Long id);

}
