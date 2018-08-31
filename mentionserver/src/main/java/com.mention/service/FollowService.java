package com.mention.service;

import com.mention.dto.FollowRq;
import com.mention.dto.ShortUserDetailsRs;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface FollowService {

  List<ShortUserDetailsRs> getFollowedUsers(String username);

  List<ShortUserDetailsRs> getFollowingUsers(String username);

  ResponseEntity<?> addFollow(FollowRq follow);

  ResponseEntity<?> deleteFollow(FollowRq follow);
}
