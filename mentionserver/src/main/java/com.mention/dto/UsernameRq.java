package com.mention.dto;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class UsernameRq {

  private String username;
}
