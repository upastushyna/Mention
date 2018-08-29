package com.mention.service;

import com.mention.dto.CommentRq;
import com.mention.model.Comment;
import com.mention.repository.CommentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {

  private CommentRepository commentRepository;

  @Autowired
  public CommentServiceImpl(CommentRepository commentRepository) {
    this.commentRepository = commentRepository;
  }

  @Override
  public void addComment(CommentRq comment) {
    ModelMapper modelMapper = new ModelMapper();
    Comment insertComment = modelMapper.map(comment, Comment.class);
    commentRepository.save(insertComment);
  }
}
