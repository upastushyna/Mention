package com.mention.dto;

import lombok.Data;

import java.util.List;

@Data
public class CurrentUserDtoRs {

  private Long id;

  private String username;

  private List<FollowDtoRs> followedUsers;

  private ProfilePicturesRs profile;

}