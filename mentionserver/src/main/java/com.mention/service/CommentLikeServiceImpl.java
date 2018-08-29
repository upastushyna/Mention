package com.mention.service;

import com.mention.dto.CommentLikeRq;
import com.mention.model.CommentLike;
import com.mention.repository.CommentLikeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
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
  public void addCommentLike(CommentLikeRq commentLikeDto) {
    ModelMapper modelMapper = new ModelMapper();
    CommentLike insertCommentLike = modelMapper.map(commentLikeDto, CommentLike.class);
    commentLikeRepository.save(insertCommentLike);
  }

  @Override
  @Transactional
  public void deleteCommentLike(CommentLikeRq commentLikeDto) {
    commentLikeRepository.deleteByUserIdAndCommentId(
        commentLikeDto.getUser().getId(),
        commentLikeDto.getComment().getId());

  }
}
