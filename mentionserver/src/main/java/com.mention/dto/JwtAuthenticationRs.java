package com.mention.dto;

import lombok.Data;

@Data
public class JwtAuthenticationRs {

  private String accessToken;
  private final String TOKEN_TYPE = "Bearer";

  public JwtAuthenticationRs(String accessToken) {
    this.accessToken = accessToken;
  }
}
