package com.mention.dto;

import lombok.Data;

import java.util.Date;

@Data
public class NotificationRs {

  private Long id;

  private String type;

  private boolean isChecked;

  private PostIdRq post;

  private ShortUserDetailsRs sender;

  private ShortUserDetailsRs receiver;

  private Date timestamp;
}
