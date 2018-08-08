package org.danit.mention.dto;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class PostDtoRs {

  private String body;

  private ShortUserDetailsRs author;

  private List<CommentDtoRs> comments;

  private Date timestamp;

  private String mediafileUrl;

  private List<PostLikeRs> likes;
}
