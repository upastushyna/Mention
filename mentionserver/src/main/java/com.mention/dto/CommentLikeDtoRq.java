package com.mention.dto;

import lombok.Data;

@Data
public class CommentLikeDtoRq {

  private UserDtoRq user;
  private CommentDtoIdRq comment;

}
