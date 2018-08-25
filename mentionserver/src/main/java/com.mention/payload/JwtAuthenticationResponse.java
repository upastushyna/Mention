package com.mention.payload;

import lombok.Data;

@Data
public class JwtAuthenticationResponse {

  private String accessToken;
  private String tokenType = "Bearer";

  public JwtAuthenticationResponse(String accessToken) {
    this.accessToken = accessToken;
  }
}
