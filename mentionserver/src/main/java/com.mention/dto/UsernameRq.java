package com.mention.dto;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class UsernameRq {

  @Length(min = 3, max = 20)
  private String username;
}
