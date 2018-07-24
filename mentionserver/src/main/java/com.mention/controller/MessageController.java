package com.mention.controller;

import com.mention.model.Message;
import com.mention.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/message")
public class MessageController {

  @Autowired
  MessageService messageService;

  @GetMapping("/{id}")
  public Message getMessage(@PathVariable Long id) {
    return messageService.getMessage(id);
  }

  @PostMapping
  @Transactional
  public void addMessage(@RequestBody Message message) {
    messageService.addMessage(message);
  }

  @PutMapping
  @Transactional
  public void updateMessage(@RequestBody Message message) {
    messageService.updateMessage(message);
  }

  @DeleteMapping("/{id}")
  @Transactional
  public void deleteMessage(Long id) {
    messageService.deleteMessage(id);
  }

}
