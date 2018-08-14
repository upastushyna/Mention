package com.mention.dto;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class CommentLikeDtoRq {

  private UserDtoIdRq user;

  @Length(min = 2, max = 280)
  private CommentDtoIdRq comment;

}

//s