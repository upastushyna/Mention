package com.mention.dto;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class UserDtoRq {

  @Length(min = 3, max = 20)
  private String username;

  @Length(min = 5, max = 20)
  private String email;

  @Length(min = 6, max = 24)
  private String password;

  private boolean isActive;

}
