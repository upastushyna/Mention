package com.mention.dao;

import com.mention.model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
public class MessageDaoImpl implements MessageDao{

  @Autowired
  EntityManager entityManager;

  @Override
  public Message getMessage(Long id) {
    Message message = entityManager.find(Message.class, id);
    return message;
  }

  @Override
  public void addMessage(Message message) {
    entityManager.persist(message);
  }

  @Override
  public void deleteMessage(Long id) {
    Message message = entityManager.find(Message.class, id);
    entityManager.remove(message);
  }

  @Override
  public void updateMessage(Message message) {
    entityManager.merge(message);
  }
}
