package com.mention.service;

import com.mention.dto.CommentDtoRq;

public interface UserCommentsService {

  void addComment(CommentDtoRq comment);
}
