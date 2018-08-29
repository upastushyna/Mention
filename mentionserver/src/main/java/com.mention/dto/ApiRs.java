package com.mention.dto;

import lombok.Data;

@Data
public class ApiRs {

  private Boolean success;
  private String message;

  public ApiRs(Boolean success, String message) {
    this.success = success;
    this.message = message;
  }

}
