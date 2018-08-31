package com.mention.service;

import com.mention.dto.ApiRs;
import com.mention.dto.CommentRq;
import com.mention.model.Comment;
import com.mention.repository.CommentRepository;
import com.mention.security.UserPrincipal;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {

  private CommentRepository commentRepository;

  @Autowired
  public CommentServiceImpl(CommentRepository commentRepository) {
    this.commentRepository = commentRepository;
  }

  @Override
  public ResponseEntity<?> addComment(CommentRq comment) {
    UserPrincipal userPrincipal = (UserPrincipal) SecurityContextHolder
        .getContext()
        .getAuthentication()
        .getPrincipal();
    if (!comment.getCommentator().getId().equals(userPrincipal.getId())) {
      return new ResponseEntity(new ApiRs(false, "Access denied"), HttpStatus.FORBIDDEN);
    }
    ModelMapper modelMapper = new ModelMapper();
    Comment insertComment = modelMapper.map(comment, Comment.class);
    commentRepository.save(insertComment);
    return ResponseEntity.ok(new ApiRs(true, "Comment added succesfully"));
  }
}
