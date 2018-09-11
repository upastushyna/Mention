package com.mention.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NotificationPopRs {

  private ShortUserDetailsRs sender;

  private ShortUserDetailsRs receiver;

  private String url;

  private String type;
}
