package com.mention.dto;

import lombok.Data;

@Data
public class ShortUserDetailsRs {

  private Long id;

  private String username;

  private ProfilePicturesRs profile;
}
