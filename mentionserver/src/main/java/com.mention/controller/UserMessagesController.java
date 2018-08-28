package com.mention.controller;

import com.mention.dto.MessageDtoRq;
import com.mention.service.UserMessagesServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/messages")
public class UserMessagesController {

  private UserMessagesServiceImpl userMessagesService;

  @Autowired
  public UserMessagesController(UserMessagesServiceImpl userMessagesService) {
    this.userMessagesService = userMessagesService;
  }

  @PostMapping("/add")
  public void addMessage(@Valid @RequestBody MessageDtoRq message) {
    userMessagesService.addMessage(message);
  }
}