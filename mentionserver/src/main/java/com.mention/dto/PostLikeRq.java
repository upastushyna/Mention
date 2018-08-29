package com.mention.dto;

import lombok.Data;

@Data
public class PostLikeRq {

  private UserIdRq user;
  private PostIdRq post;

}
