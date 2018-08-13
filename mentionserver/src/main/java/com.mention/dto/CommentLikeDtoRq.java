package com.mention.dto;

import lombok.Data;

@Data
public class CommentLikeDtoRq {

  private UserDtoIdRq user;

  private CommentDtoIdRq comment;

}
