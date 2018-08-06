package com.mention.service002;

import com.mention.model.PostLike;
import com.mention.repository.PostLikeRepository;
import com.mention.repository.PostLikeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LikeServiceImpl implements LikeService {

  private PostLikeRepository postLikeRepository;

  @Autowired
  public LikeServiceImpl(PostLikeRepository postLikeRepository) {
    this.postLikeRepository = postLikeRepository;
  }

  @Override
  public Optional<PostLike> getLike(Long id) {
    return postLikeRepository.findById(id);
  }

  @Override
  public void addLike(PostLike postLike) {
    postLikeRepository.save(postLike);
  }

  @Override
  public void deleteLike(Long id) {
    postLikeRepository.deleteById(id);

  }
}
