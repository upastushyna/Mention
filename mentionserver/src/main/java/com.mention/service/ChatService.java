package com.mention.service;

import com.mention.dto.ChatDto;

public interface ChatService {
  ChatDto getSingleChat(String username1, String username2);
}
