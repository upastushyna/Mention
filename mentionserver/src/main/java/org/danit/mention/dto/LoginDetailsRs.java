package org.danit.mention.dto;

import lombok.Data;

@Data
public class LoginDetailsRs {

  private boolean result;

  private String message;

  protected LoginDetailsRs(){

  }

  public LoginDetailsRs(boolean result, String message) {
    this.result = result;
    this.message = message;
  }

  public LoginDetailsRs(boolean result) {
    this(result, "");
  }
}
