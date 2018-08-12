package com.mention.service;

import com.mention.dto.CommentLikeDtoRq;
import com.mention.model.CommentLike;
import com.mention.repository.CommentLikeRepository;
import com.mention.repository.PostRepository;
import com.mention.repository.UserRepository;
import lombok.Data;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Data
@Service
public class CommentLikeServiceImpl implements CommentLikeService {

  private CommentLikeRepository commentLikeRepository;

  @Autowired
  public CommentLikeServiceImpl(CommentLikeRepository commentLikeRepository) {
    this.commentLikeRepository = commentLikeRepository;
  }

  @Override
  @Transactional
  public void addCommentLike(CommentLikeDtoRq commentLikeDto) {
    ModelMapper modelMapper = new ModelMapper();
    CommentLike insertCommentLike = modelMapper.map(commentLikeDto, CommentLike.class);
    commentLikeRepository.save(insertCommentLike);

  }

  @Override
  @Transactional
  public void deleteCommentLike(CommentLikeDtoRq commentLikeDto) {
    commentLikeRepository.deleteByUserIdAndCommentId(
        commentLikeDto.getUser().getId(),
        commentLikeDto.getComment().getId());

  }
}
