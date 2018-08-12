package com.mention.dto;

import lombok.Data;

@Data
public class MessageDtoRq {

  private String content;

  private UserDtoIdRq sender;

  private UserDtoIdRq receiver;

  private ChatDtoIdRq chat;
}
