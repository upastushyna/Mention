package com.mention.service;

import com.mention.dto.PostLikeRq;
import com.mention.model.PostLike;
import com.mention.repository.PostLikeRepository;
import com.mention.repository.PostRepository;
import com.mention.repository.UserRepository;
import lombok.Data;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Data
@Service
public class PostLikeServiceImpl implements PostLikeService {

  private PostLikeRepository postLikeRepository;
  private UserRepository userRepository;
  private PostRepository postRepository;

  @Autowired
  public PostLikeServiceImpl(PostLikeRepository postLikeRepository) {
    this.postLikeRepository = postLikeRepository;
  }

  @Override
  @Transactional
  public void addPostLike(PostLikeRq postLike) {
    ModelMapper modelMapper = new ModelMapper();
    PostLike insertPostLike = modelMapper.map(postLike, PostLike.class);
    postLikeRepository.save(insertPostLike);
  }

  @Override
  @Transactional
  public void deletePostLike(PostLikeRq postLike) {
    postLikeRepository.deleteByUserIdAndPostId(
        postLike.getUser().getId(),
        postLike.getPost().getId());
  }


}
