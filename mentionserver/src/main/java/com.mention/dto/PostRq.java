package com.mention.dto;

import lombok.Data;
import org.hibernate.validator.constraints.Length;


@Data
public class PostRq {

  private Long id;

  @Length(min = 2, max = 280)
  private String body;

  private String mediaFileUrl;

  private UserIdRq author;

  private PostIdRq parent;
}
