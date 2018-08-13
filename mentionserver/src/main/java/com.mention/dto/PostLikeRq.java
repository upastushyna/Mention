package com.mention.dto;

import lombok.Data;

@Data
public class PostLikeRq {

  private UserDtoIdRq user;
  private PostDtoIdRq post;

}
