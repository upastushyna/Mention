package com.mention.dto;

import lombok.Data;
import java.util.Date;

@Data
public class MessageDtoRs {

  private String content;

  private ShortUserDetailsRs sender;

  private ShortUserDetailsRs receiver;

  private Date timestamp;
}
