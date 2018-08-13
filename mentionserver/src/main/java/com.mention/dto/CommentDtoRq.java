package com.mention.dto;

import lombok.Data;

@Data
public class CommentDtoRq {

  private String body;

  private UserDtoIdRq commentator;

  private PostDtoIdRq post;

  private String mediaFileUrl;
}
