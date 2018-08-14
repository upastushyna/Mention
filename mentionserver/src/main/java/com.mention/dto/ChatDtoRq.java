package com.mention.dto;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class ChatDtoRq {

  @Length(min = 3, max = 20)
  private UserDtoUsernameRq user1;

  @Length(min = 3, max = 20)
  private UserDtoUsernameRq user2;

}
