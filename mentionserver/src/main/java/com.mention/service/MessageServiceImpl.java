package com.mention.service;

import com.mention.dao.MessageDao;
import com.mention.model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageServiceImpl implements MessageService {

  @Autowired
  MessageDao messageDao;

  @Override
  public Message getMessage(Long id) {
    return messageDao.getMessage(id);
  }

  @Override
  public void addMessage(Message message) {
    messageDao.addMessage(message);
  }

  @Override
  public void deleteMessage(Long id) {
    messageDao.deleteMessage(id);
  }

  @Override
  public void updateMessage(Message message) {
    messageDao.updateMessage(message);
  }
}
