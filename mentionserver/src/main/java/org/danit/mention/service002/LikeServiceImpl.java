package org.danit.mention.service002;

import org.danit.mention.model.PostLike;
import org.danit.mention.repository.CommentLikeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LikeServiceImpl implements LikeService {

  private CommentLikeRepository postLikeRepository;

  @Autowired
  public LikeServiceImpl(CommentLikeRepository postLikeRepository) {
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
