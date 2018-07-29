package com.mention.service;

import com.mention.dao.LikesDao;
import com.mention.model.Likes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class LikesServiceImpl implements LikesService {

  private LikesDao likesDao;

  @Autowired
  public LikesServiceImpl(LikesDao likesDao){
    this.likesDao = likesDao;
  }

  @Override
  @Transactional
  public Optional<Likes> getLike(Long id) {
    return likesDao.findById(id);
  }

  @Override
  @Transactional
  public void addLike(Likes likes) {
    likesDao.save(likes);
  }

  @Override
  public void deleteLike(Long id) {
    likesDao.deleteById(id);
  }
}
