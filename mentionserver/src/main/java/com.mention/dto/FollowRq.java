package com.mention.dto;

import lombok.Data;

@Data
public class FollowRq {

  private UserIdRq follower;

  private UserIdRq followedUser;
}
