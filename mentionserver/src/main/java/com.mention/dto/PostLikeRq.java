package com.mention.dto;

import lombok.Data;

@Data
public class PostLikeRq {

  private UserDtoRq user;
  private PostDtoIdRq post;

}
