package com.mention.dto;

import lombok.Data;

@Data
public class FollowDtoRq {

  private Long id;

  private UserDtoIdRq follower;

  private UserDtoIdRq followedUser;
}
