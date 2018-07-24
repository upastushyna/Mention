package com.mention.dao;

import com.mention.model.Message;

public interface MessageDao {

  Message getMessage(Long id);

  void addMessage(Message message);

  void deleteMessage(Long id);

  void updateMessage(Message message);

}
