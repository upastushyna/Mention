package com.mention.controller;

import com.mention.dto.ChatDtoRs;
import com.mention.service.UserChatsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserChatsController {

  private UserChatsServiceImpl userChatsService;

  @Autowired
  public UserChatsController(UserChatsServiceImpl userChatsService) {
    this.userChatsService = userChatsService;
  }

  @GetMapping("/chats/{username}")
  public List<ChatDtoRs> getChatsByUsername(@PathVariable String username) {
    return userChatsService.getChatsByUsername(username);
  }

}
