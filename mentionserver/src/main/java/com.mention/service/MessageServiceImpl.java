package com.mention.service;

import com.mention.dao.MessageDao;
import com.mention.model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
  public void addMessage(Message message) {
    messageDao.save(message);
  }

  @Override
  public void deleteMessage(Long id) {
    messageDao.deleteById(id);
  }

  @Override
  public void updateMessage(Message message) {
    messageDao.save(message);
  }
}
