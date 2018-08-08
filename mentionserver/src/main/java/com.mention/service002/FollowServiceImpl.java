package com.mention.service002;

import com.mention.repository.FollowRepository;
import com.mention.model.Follow;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class FollowServiceImpl implements FollowService {

  private FollowRepository followRepository;

  public FollowServiceImpl(FollowRepository followRepository) {
    this.followRepository = followRepository;
  }

  @Override
  @Transactional
  public void addFollow(Follow follow) {
    followRepository.save(follow);
  }


  @Override
  @Transactional
  public void deleteFollow(Long id) {
    followRepository.deleteById(id);

  }

  @Override
  public Optional<Follow> getFollow(Long id) {
    return followRepository.findById(id);
  }
}
