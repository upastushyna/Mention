package com.mention.service;

import com.mention.dto.ChatRq;
import com.mention.dto.ChatRs;
import java.util.List;

public interface ChatService {

  List<ChatRs> getChatsByUsername(String username);

  ChatRs getChatByUsernames(String username1, String username2);

  void addChat(ChatRq chat);
}
