package com.mention.service;

import com.mention.dao.LikeRepository;
import com.mention.model.Lyke;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LikeServiceImpl implements LikeService {

  private LikeRepository likeRepository;

  @Autowired
  public LikeServiceImpl(LikeRepository likeRepository){
    this.likeRepository = likeRepository;
  }

  @Override
  public Optional<Lyke> getLike(Long id) {
    return likeRepository.findById(id);
  }

  @Override
  public void addLike(Lyke lyke) {
    likeRepository.save(lyke);
  }

  @Override
  public void deleteLike(Long id) {
    likeRepository.deleteById(id);

  }
}
