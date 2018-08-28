package com.mention.dto;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class CommentRs {

  private Long id;

  private String body;

  private ShortUserDetailsRs commentator;

  private Date timestamp;

  private String mediafileUrl;

  private List<CommentLikeRs> commentLikes;

}
