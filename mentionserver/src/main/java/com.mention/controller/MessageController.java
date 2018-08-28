package com.mention.controller;

import com.mention.dto.MessageRq;
import com.mention.service.MessageServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/messages")
public class MessageController {

  private MessageServiceImpl userMessagesService;

  @Autowired
  public MessageController(MessageServiceImpl userMessagesService) {
    this.userMessagesService = userMessagesService;
  }

  @PostMapping("/add")
  public void addMessage(@Valid @RequestBody MessageRq message) {
    userMessagesService.addMessage(message);
  }
}
