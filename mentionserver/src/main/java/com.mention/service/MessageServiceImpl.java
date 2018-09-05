package com.mention.service;

import com.mention.dto.ApiRs;
import com.mention.dto.MessageRq;
import com.mention.dto.WsMessageRs;
import com.mention.model.Message;
import com.mention.repository.MessageRepository;
import com.mention.security.UserPrincipal;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MessageServiceImpl implements MessageService {

  private MessageRepository messageRepository;

  private SimpMessagingTemplate template;

  private final String wsPath = "/front/endpoint1";

  @Autowired
  public MessageServiceImpl(MessageRepository messageRepository, SimpMessagingTemplate template) {
    this.messageRepository = messageRepository;
    this.template = template;
  }

  @Override
  @Transactional
  public ResponseEntity<?> addMessage(MessageRq message) {
    UserPrincipal userPrincipal = (UserPrincipal) SecurityContextHolder
        .getContext()
        .getAuthentication()
        .getPrincipal();
    if (!message.getSender().getId().equals(userPrincipal.getId())) {
      return new ResponseEntity(new ApiRs(false, "Access denied"), HttpStatus.FORBIDDEN);
    }

    ModelMapper modelMapper = new ModelMapper();
    Message insertMessage = modelMapper.map(message, Message.class);
    messageRepository.save(insertMessage);
    template.convertAndSend(wsPath, new WsMessageRs(message.getReceiver().getUsername(), userPrincipal.getUsername()));
    return ResponseEntity.ok(new ApiRs(true, "Message sent successfully"));
  }
}
