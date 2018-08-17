package com.mention.dto;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class CommentDtoRq {

  @Length(min = 1, max = 280)
  private String body;

  private UserDtoIdRq commentator;

  private PostDtoIdRq post;

  private String mediaFileUrl;
}
