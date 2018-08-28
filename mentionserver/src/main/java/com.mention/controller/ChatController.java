package com.mention.controller;

import com.mention.dto.ChatRq;
import com.mention.dto.ChatRs;
import com.mention.service.ChatServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/chats")
public class ChatController {

  private ChatServiceImpl userChatsService;

  @Autowired
  public ChatController(ChatServiceImpl userChatsService) {
    this.userChatsService = userChatsService;
  }

  @GetMapping("/{username}")
  public List<ChatRs> getChatsByUsername(@PathVariable String username) {
    return userChatsService.getChatsByUsername(username);
  }

  @GetMapping("/c/user1={username1}&user2={username2}")
  public ChatRs getChatByUsernames(@PathVariable String username1, @PathVariable String username2) {
    return userChatsService.getChatByUsernames(username1, username2);
  }

  @PostMapping("/add")
  public void addChat(@RequestBody ChatRq chat) {
    System.out.println(chat);
    userChatsService.addChat(chat);
  }
}
