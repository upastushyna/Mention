package com.mention.dto;

import com.mention.model.Comment;
import com.mention.model.Like;
import com.mention.model.User;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class PostDto {

  private String body;

  private PostUserDetails author;

  private List<PostComment> comments;

  private Date timestamp;

  private String mediafileUrl;

  private List<PostLike> likes;
}
