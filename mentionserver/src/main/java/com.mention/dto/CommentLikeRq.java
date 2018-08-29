package com.mention.dto;

import lombok.Data;

@Data
public class CommentLikeRq {

  private UserIdRq user;

  private CommentIdRq comment;

}
