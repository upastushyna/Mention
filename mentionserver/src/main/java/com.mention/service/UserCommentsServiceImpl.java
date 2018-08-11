package com.mention.service;

import com.mention.dto.CommentDtoRq;
import com.mention.model.Comment;
import com.mention.repository.CommentRepository;
import lombok.Data;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserCommentsServiceImpl implements UserCommentsService {

  private CommentRepository commentRepository;

  @Autowired
  public UserCommentsServiceImpl(CommentRepository commentRepository) {
    this.commentRepository = commentRepository;
  }

  @Override
  public void addComment(CommentDtoRq comment) {
    ModelMapper modelMapper = new ModelMapper();
    Comment insertComment = modelMapper.map(comment, Comment.class);
    commentRepository.save(insertComment);
  }
}
