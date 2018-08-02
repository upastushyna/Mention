package com.mention.dto;

import com.mention.model.Message;
import lombok.Data;

import java.util.List;

@Data
public class ChatDto {

  private PostUserDetails user2;

  private List<MessageDto> messages;


}
