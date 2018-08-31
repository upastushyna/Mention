package com.mention.service;

import com.mention.dto.ApiRs;
import com.mention.dto.PostLikeRq;
import com.mention.model.PostLike;
import com.mention.repository.PostLikeRepository;
import com.mention.repository.PostRepository;
import com.mention.repository.UserRepository;
import com.mention.security.UserPrincipal;
import lombok.Data;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PostLikeServiceImpl implements PostLikeService {

  private PostLikeRepository postLikeRepository;

  @Autowired
  public PostLikeServiceImpl(PostLikeRepository postLikeRepository) {
    this.postLikeRepository = postLikeRepository;
  }

  @Override
  @Transactional
  public ResponseEntity<?> addPostLike(PostLikeRq postLike) {
    UserPrincipal userPrincipal = (UserPrincipal) SecurityContextHolder
        .getContext()
        .getAuthentication()
        .getPrincipal();
    if (!postLike.getUser().getId().equals(userPrincipal.getId())) {
      return new ResponseEntity(new ApiRs(false, "Access denied"), HttpStatus.FORBIDDEN);
    }

    ModelMapper modelMapper = new ModelMapper();
    PostLike insertPostLike = modelMapper.map(postLike, PostLike.class);
    postLikeRepository.save(insertPostLike);
    return ResponseEntity.ok(new ApiRs(true, "Liked successfully"));
  }

  @Override
  @Transactional
  public ResponseEntity<?> deletePostLike(PostLikeRq postLike) {
    UserPrincipal userPrincipal = (UserPrincipal) SecurityContextHolder
        .getContext()
        .getAuthentication()
        .getPrincipal();
    if (!postLike.getUser().getId().equals(userPrincipal.getId())) {
      return new ResponseEntity(new ApiRs(false, "Access denied"), HttpStatus.FORBIDDEN);
    }

    postLikeRepository.deleteByUserIdAndPostId(
        postLike.getUser().getId(),
        postLike.getPost().getId());
    return ResponseEntity.ok(new ApiRs(true, "Like removed successfully"));
  }


}
