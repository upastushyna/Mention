package com.mention.dto;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class ChangePasswordRq {

  @Length(min = 6, max = 24)
  private String password;
}
