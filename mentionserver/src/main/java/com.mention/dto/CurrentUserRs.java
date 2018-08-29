package com.mention.dto;

import lombok.Data;

import java.util.List;

@Data
public class CurrentUserRs {

  private Long id;

  private String username;

  private List<FollowRs> followedUsers;

  private ProfilePicturesRs profile;

}