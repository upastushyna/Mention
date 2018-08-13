package com.mention.dto;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class PostDtoRs {

  private Long id;

  private String body;

  private ShortUserDetailsRs author;

  private List<CommentDtoRs> comments;

  private Date timestamp;

  private String mediaFileUrl;

  private List<PostLikeRs> likes;

  private List<PostDtoIdRq> children;

  private PostDtoRs parent;
}
