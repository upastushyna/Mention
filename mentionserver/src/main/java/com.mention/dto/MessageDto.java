package com.mention.dto;

import lombok.Data;

import java.util.Date;

@Data
public class MessageDto {

  private String content;

  private PostUserDetails sender;

  private PostUserDetails receiver;

  private Date timestamp;
}
