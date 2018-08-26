package com.mention.dto;

import lombok.Data;
import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.Email;

@Data
public class UserDtoRq {

  @Length(min = 3, max = 20)
  private String username;

  @Email
  private String email;

  @Length(min = 6, max = 24)
  private String password;

  private boolean isActive;

}
