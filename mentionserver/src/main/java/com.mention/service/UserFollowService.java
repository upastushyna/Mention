package com.mention.service;

import com.mention.dto.ShortUserDetailsRs;

import java.util.List;

public interface UserFollowService {

  List<ShortUserDetailsRs> getFollowedUsers(String username);

  List<ShortUserDetailsRs> getFollowingUsers(String username);
}
