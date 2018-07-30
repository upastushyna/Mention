package com.mention.service;

import com.mention.model.Follow;
import com.mention.model.User;

import java.util.Optional;

public interface FollowService {

  void addFollow(Follow follow);

  void deleteFollow(Long id);

  Optional<Follow> getFollow(Long id);


}
