package com.mention.dto;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class ChatDtoRq {

  private UserDtoUsernameRq user1;

  private UserDtoUsernameRq user2;

}
