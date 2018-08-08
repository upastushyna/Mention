package org.danit.mention.dto;

import lombok.Data;

import java.util.Date;

@Data
public class CommentDtoRs {

  private String body;

  private ShortUserDetailsRs commentator;

  private Date timestamp;

  private String mediafileUrl;

}
