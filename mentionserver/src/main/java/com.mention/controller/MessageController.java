package com.mention.controller;

import com.mention.model.Message;
import com.mention.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/message")
public class MessageController {

  private MessageService messageService;

  @Autowired
  public MessageController(MessageService messageService) {
    this.messageService = messageService;
  }

  @GetMapping("/{id}")
  public Optional<Message> getMessage(@PathVariable Long id) {
    return messageService.getMessage(id);
  }

  @PostMapping
  public void addMessage(@RequestBody Message message) {
    messageService.addMessage(message);
  }

  @PutMapping
  public void updateMessage(@RequestBody Message message) {
    messageService.updateMessage(message);
  }

  @DeleteMapping("/{id}")
  public void deleteMessage(@PathVariable Long id) {
    messageService.deleteMessage(id);
  }

}
