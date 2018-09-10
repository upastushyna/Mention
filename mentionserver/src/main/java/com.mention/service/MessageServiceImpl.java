package com.mention.service;

import com.mention.config.Constants;
import com.mention.dto.ApiRs;
import com.mention.dto.MessageRq;
import com.mention.dto.NotificationRs;
import com.mention.dto.WsMessageRs;
import com.mention.model.Message;
import com.mention.model.Notification;
import com.mention.repository.MessageRepository;
import com.mention.repository.NotificationRepository;
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

  private NotificationRepository notificationRepository;

  private final String wsPath = "/queue/chat";

  @Autowired
  public MessageServiceImpl(MessageRepository messageRepository, SimpMessagingTemplate template, NotificationRepository notificationRepository) {
    this.messageRepository = messageRepository;
    this.template = template;
    this.notificationRepository = notificationRepository;
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

    String frontUrl = "/messages/" + userPrincipal.getUsername();
    Notification notification = new Notification(frontUrl, Constants.MESSAGE, userPrincipal.getUser());

    notificationRepository.save(notification);
    template.convertAndSendToUser(message.getReceiver().getUsername(), wsPath,
        new WsMessageRs(message.getReceiver().getUsername(),
            userPrincipal.getUsername()));
    template.convertAndSendToUser(message.getReceiver().getUsername(),
        Constants.WS_NOTIFY,
        modelMapper.map(notification, NotificationRs.class));

    return ResponseEntity.ok(new ApiRs(true, "Message sent successfully"));

  }
}
