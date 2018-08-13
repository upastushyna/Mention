package com.mention.dto;

import lombok.Data;

@Data
public class UserDtoRq {

  private String username;

  private String email;

  private String password;

  private boolean isActive;

}
