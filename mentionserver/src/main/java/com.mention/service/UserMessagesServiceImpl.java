package com.mention.service;

import com.mention.dto.MessageDtoRq;
import com.mention.model.Message;
import com.mention.repository.MessageRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserMessagesServiceImpl implements UserMessagesService {

  private MessageRepository messageRepository;

  @Autowired
  public UserMessagesServiceImpl(MessageRepository messageRepository) {
    this.messageRepository = messageRepository;
  }

  @Override
  @Transactional
  public void addMessage(MessageDtoRq message) {
    ModelMapper modelMapper = new ModelMapper();
    Message insertMessage = modelMapper.map(message, Message.class);
    messageRepository.save(insertMessage);
  }
}
