package com.mention.service;

import com.mention.dto.FollowDtoRq;
import com.mention.dto.ShortUserDetailsRs;
import com.mention.dto.UserDtoIdRq;

import java.util.List;

public interface UserFollowService {

  List<ShortUserDetailsRs> getFollowedUsers(String username);

  List<ShortUserDetailsRs> getFollowingUsers(String username);

  void addFollow(UserDtoIdRq follower, UserDtoIdRq followedUser);

  void deleteFollow(UserDtoIdRq follower, UserDtoIdRq followedUser);
}
