package com.mention.service;

import com.mention.dao.MessageDao;
import com.mention.model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class MessageServiceImpl implements MessageService {

  private MessageDao messageDao;

  @Autowired
  public MessageServiceImpl(MessageDao messageDao) {
    this.messageDao = messageDao;
  }

  @Override
  public Optional<Message> getMessage(Long id) {
    return messageDao.findById(id);
  }

  @Override
  @Transactional
  public void addMessage(Message message) {
    messageDao.save(message);
  }

  @Override
  @Transactional
  public void deleteMessage(Long id) {
    messageDao.deleteById(id);
  }

  @Override
  @Transactional
  public void updateMessage(Message message) {
    messageDao.save(message);
  }
}
