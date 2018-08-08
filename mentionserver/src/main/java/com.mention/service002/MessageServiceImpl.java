package com.mention.service002;

import com.mention.repository.MessageRepository;
import com.mention.model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class MessageServiceImpl implements MessageService {

  private MessageRepository messageRepository;

  @Autowired
  public MessageServiceImpl(MessageRepository messageRepository) {
    this.messageRepository = messageRepository;
  }

  @Override
  public Optional<Message> getMessage(Long id) {
    return messageRepository.findById(id);
  }

  @Override
  @Transactional
  public void addMessage(Message message) {
    messageRepository.save(message);
  }

  @Override
  @Transactional
  public void deleteMessage(Long id) {
    messageRepository.deleteById(id);
  }

  @Override
  @Transactional
  public void updateMessage(Message message) {
    messageRepository.save(message);
  }
}
