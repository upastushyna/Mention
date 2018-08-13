package com.mention.dto;

import lombok.Data;

@Data
public class UserDtoNewUserRq {

  private String username;
  private String email;
  private String password;
  // we need parameter 'isActive' in this Dto ?
  private boolean isActive;


}
