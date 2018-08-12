package com.mention.dto;

import lombok.Data;

@Data
public class PostDtoRq {

  private String body;

  private String mediaFileUrl;

  private UserDtoIdRq author;
}
