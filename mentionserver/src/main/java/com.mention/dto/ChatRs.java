package com.mention.dto;

import lombok.Data;

import java.util.List;

@Data
public class ChatRs {

  private Long id;

  private ShortUserDetailsRs user1;

  private ShortUserDetailsRs user2;

  private List<MessageRs> messages;
}
