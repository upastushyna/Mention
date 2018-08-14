package com.mention.dto;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;


@Data
public class PostDtoRq {

  private Long id;

  @Length(min = 2, max = 280)
  private String body;

  private String mediaFileUrl;

  private UserDtoIdRq author;

  private PostDtoIdRq parent;
}
