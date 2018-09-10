package com.mention.dto;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class PostRs {

  private Long id;

  private String body;

  private ShortUserDetailsRs author;

  private List<CommentRs> comments;

  private Date timestamp;

  private String mediaFileUrl;

  private List<PostLikeRs> likes;

  private List<RepostRs> children;

  private PostRs parent;
}
