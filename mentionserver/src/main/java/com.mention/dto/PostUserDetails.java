package com.mention.dto;

import com.mention.model.Profile;
import lombok.Data;

@Data
public class PostUserDetails {

  private String username;

  private ProfileAvatar profile;
}
