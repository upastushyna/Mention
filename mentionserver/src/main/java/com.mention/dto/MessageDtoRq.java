package com.mention.dto;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class MessageDtoRq {

  @Length(min = 1, max = 1000)
  private String content;

  private UserDtoIdRq sender;

  private UserDtoIdRq receiver;

  private ChatDtoIdRq chat;
}
