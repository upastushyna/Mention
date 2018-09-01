package com.mention.service;

import com.mention.dto.ApiRs;
import com.mention.dto.CommentLikeRq;
import com.mention.model.CommentLike;
import com.mention.repository.CommentLikeRepository;
import com.mention.security.UserPrincipal;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CommentLikeServiceImpl implements CommentLikeService {

  private CommentLikeRepository commentLikeRepository;

  @Autowired
  public CommentLikeServiceImpl(CommentLikeRepository commentLikeRepository) {
    this.commentLikeRepository = commentLikeRepository;
  }

  @Override
  @Transactional
  public ResponseEntity<?> addCommentLike(CommentLikeRq commentLikeDto) {
    UserPrincipal userPrincipal = (UserPrincipal) SecurityContextHolder
        .getContext()
        .getAuthentication()
        .getPrincipal();
    if (!commentLikeDto.getUser().getId().equals(userPrincipal.getId())) {
      return new ResponseEntity(new ApiRs(false, "Access denied"), HttpStatus.FORBIDDEN);
    }

    ModelMapper modelMapper = new ModelMapper();
    CommentLike insertCommentLike = modelMapper.map(commentLikeDto, CommentLike.class);
    commentLikeRepository.save(insertCommentLike);
    return ResponseEntity.ok(new ApiRs(true, "Liked successfully"));
  }

  @Override
  @Transactional
  public ResponseEntity<?> deleteCommentLike(CommentLikeRq commentLikeDto) {
    UserPrincipal userPrincipal = (UserPrincipal) SecurityContextHolder
        .getContext()
        .getAuthentication()
        .getPrincipal();
    if (!commentLikeDto.getUser().getId().equals(userPrincipal.getId())) {
      return new ResponseEntity(new ApiRs(false, "Access denied"), HttpStatus.FORBIDDEN);
    }

    commentLikeRepository.deleteByUserIdAndCommentId(
        commentLikeDto.getUser().getId(),
        commentLikeDto.getComment().getId());
    return ResponseEntity.ok(new ApiRs(true, "Like removed successfully"));

  }
}
