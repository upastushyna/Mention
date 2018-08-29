package com.mention.service;

import com.mention.dto.FollowRq;
import com.mention.dto.ShortUserDetailsRs;

import java.util.List;

public interface FollowService {

  List<ShortUserDetailsRs> getFollowedUsers(String username);

  List<ShortUserDetailsRs> getFollowingUsers(String username);

  void addFollow(FollowRq follow);

  void deleteFollow(FollowRq follow);
}
