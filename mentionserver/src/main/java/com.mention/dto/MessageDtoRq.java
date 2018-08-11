package com.mention.dto;

import lombok.Data;

@Data
public class MessageDtoRq {

  private String content;

  private UserDtoRq sender;

  private UserDtoRq receiver;
}
