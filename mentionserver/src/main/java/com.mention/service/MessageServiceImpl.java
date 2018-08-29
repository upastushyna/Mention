package com.mention.service;

import com.mention.dto.MessageRq;
import com.mention.model.Message;
import com.mention.repository.MessageRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MessageServiceImpl implements MessageService {

  private MessageRepository messageRepository;

  @Autowired
  public MessageServiceImpl(MessageRepository messageRepository) {
    this.messageRepository = messageRepository;
  }

  @Override
  @Transactional
  public void addMessage(MessageRq message) {
    ModelMapper modelMapper = new ModelMapper();
    Message insertMessage = modelMapper.map(message, Message.class);
    messageRepository.save(insertMessage);
  }
}
