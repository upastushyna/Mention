package com.mention.dto;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class MessageRq {

  @Length(min = 1, max = 1000)
  private String content;

  private ShortUserDetailsRs sender;

  private ShortUserDetailsRs receiver;

  private ChatIdRq chat;
}
