package com.mention.service;

import com.mention.model.Follow;
import com.mention.model.User;

import java.util.List;
import java.util.Optional;

public interface FollowService {

  List<Follow> getUsersFollows(Long id);

  void followUser(Long id);

  void unfollowUser(Long id);


}
