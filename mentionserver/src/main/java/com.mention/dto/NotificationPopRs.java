package com.mention.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NotificationPopRs {

  private Long id;

  private ShortUserDetailsRs sender;

  private ShortUserDetailsRs receiver;

  private PostIdRq post;

  private String url;

  private String type;
}
