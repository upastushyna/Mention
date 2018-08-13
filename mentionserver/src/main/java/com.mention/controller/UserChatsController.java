package com.mention.controller;

import com.mention.dto.ChatDtoRq;
import com.mention.dto.ChatDtoRs;
import com.mention.service.UserChatsServiceImpl;
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
public class UserChatsController {

  private UserChatsServiceImpl userChatsService;

  @Autowired
  public UserChatsController(UserChatsServiceImpl userChatsService) {
    this.userChatsService = userChatsService;
  }

  @GetMapping("/{username}")
  public List<ChatDtoRs> getChatsByUsername(@PathVariable String username) {
    return userChatsService.getChatsByUsername(username);
  }

  @GetMapping("/c/user1={username1}&user2={username2}")
  public ChatDtoRs getChatByUsernames(@PathVariable String username1, @PathVariable String username2) {
    return userChatsService.getChatByUsernames(username1, username2);
  }

  @PostMapping("/add")
  public void addChat(@RequestBody ChatDtoRq chat) {
    System.out.println(chat);
    userChatsService.addChat(chat);
  }
}
