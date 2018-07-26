package com.mention.service;

import com.mention.dao.LikeDao;
import com.mention.model.Like;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class LikeServiceImpl implements LikeService {

  private LikeDao likeDao;

  @Autowired
  public LikeServiceImpl(LikeDao likeDao){
    this.likeDao = likeDao;
  }

  @Override
  @Transactional
  public Optional<Like> getLike(Long id) {
    return likeDao.findById(id);
  }

  @Override
  @Transactional
  public void addLike(Like like) {
    likeDao.save(like);
  }

  @Override
  public void deleteLike(Like like) {
    likeDao.delete(like);
  }
}
