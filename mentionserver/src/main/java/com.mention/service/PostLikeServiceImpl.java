package com.mention.service;

import com.mention.dto.PostLikeRq;
import com.mention.model.PostLike;
import com.mention.repository.PostLikeRepository;
import lombok.Data;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Data
@Service
public class PostLikeServiceImpl implements PostLikeService {

  private PostLikeRepository postLikeRepository;

  @Autowired
  public PostLikeServiceImpl(PostLikeRepository postLikeRepository) {
    this.postLikeRepository = postLikeRepository;
  }

  @Override
  public void addPostLike(PostLikeRq postLike) {
    ModelMapper modelMapper = new ModelMapper();
    PostLike insertPostLike = modelMapper.map(postLike, PostLike.class);
    postLikeRepository.save(insertPostLike);


  }
}
