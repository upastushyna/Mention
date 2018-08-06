package com.mention.service002;

import com.mention.model.PostComment;

import java.util.Optional;

public interface CommentService {

  void addComment(PostComment postComment);

  void updateComment(PostComment postComment);

  void deleteComment(Long id);

  Optional<PostComment> getComment(Long id);

}
