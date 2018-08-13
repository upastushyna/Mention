package com.mention.dto;

import lombok.Data;

@Data
public class PostDtoRq {

  private Long id;

  private String body;

  private String mediaFileUrl;

  private UserDtoIdRq author;

  private PostDtoIdRq parent;
}
