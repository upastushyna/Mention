package com.mention.service;

import com.mention.dto.ChatDtoRq;
import com.mention.dto.ChatDtoRs;
import java.util.List;

public interface UserChatsService {

  List<ChatDtoRs> getChatsByUsername(String username);

  ChatDtoRs getChatByUsernames(String username1, String username2);

  void addChat(ChatDtoRq chat);
}
