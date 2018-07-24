package com.mention.service;

import com.mention.model.Message;

public interface MessageService {

  Message getMessage(Long id);

  void addMessage(Message message);

  void deleteMessage(Long id);

  void updateMessage(Message message);
}
