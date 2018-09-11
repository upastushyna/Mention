package com.mention.service;

import com.mention.dto.ApiRs;
import com.mention.dto.CommentIdRq;
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
import org.springframework.transaction.annotation.Transactional;

@Service
public class CommentServiceImpl implements CommentService {

  private CommentRepository commentRepository;

  private ModelMapper modelMapper;

  @Autowired
  public CommentServiceImpl(CommentRepository commentRepository) {
    this.commentRepository = commentRepository;
    this.modelMapper = new ModelMapper();
  }

  @Override
  public ResponseEntity<?> addComment(CommentRq comment) {
    UserPrincipal userPrincipal = UserPrincipal.getPrincipal();
    if (!comment.getCommentator().getId().equals(userPrincipal.getId())) {
      return new ResponseEntity(new ApiRs(false, "Access denied"), HttpStatus.FORBIDDEN);
    }

    Comment insertComment = modelMapper.map(comment, Comment.class);
    commentRepository.save(insertComment);
    return ResponseEntity.ok(new ApiRs(true, "Comment added succesfully"));
  }

  @Override
  @Transactional
  public ResponseEntity<?> deleteComment(CommentIdRq comment) {
    Comment deletedComment = commentRepository.findById(comment.getId()).get();
    UserPrincipal userPrincipal = UserPrincipal.getPrincipal();
    if (!deletedComment.getCommentator().getId().equals(userPrincipal.getId())) {
      return new ResponseEntity(new ApiRs(false, "Access denied"), HttpStatus.FORBIDDEN);
    }

    commentRepository.deleteById(comment.getId());
    return ResponseEntity.ok(new ApiRs(true, "Deleted successfully"));
  }


}
