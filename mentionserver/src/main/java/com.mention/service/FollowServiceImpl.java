package com.mention.service;

import com.mention.dao.FollowDao;
import com.mention.model.Follow;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class FollowServiceImpl implements FollowService {

  private FollowDao followDao;

  public FollowServiceImpl(FollowDao followDao) {
    this.followDao = followDao;
  }

  @Override
  @Transactional
  public void addFollow(Follow follow) {
    followDao.save(follow);
  }


  @Override
  @Transactional
  public void deleteFollow(Long id) {
    followDao.deleteById(id);

  }

  @Override
  public Optional<Follow> getFollow(Long id) {
    return followDao.findById(id);
  }
}
