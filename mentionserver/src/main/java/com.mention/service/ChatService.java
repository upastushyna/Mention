package com.mention.service;

import com.mention.dto.ChatRq;
import com.mention.dto.ChatRs;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ChatService {

  ResponseEntity<?> getChatsForCurrentUser();

  ResponseEntity<?> getChatByUsernames(String username1, String username2);

  ResponseEntity<?> addChat(ChatRq chat);
}
