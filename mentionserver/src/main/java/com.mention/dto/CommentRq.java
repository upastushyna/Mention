package com.mention.dto;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class CommentRq {

  @Length(min = 1, max = 280)
  private String body;

  private UserIdRq commentator;

  private PostIdRq post;

  private String mediaFileUrl;
}
