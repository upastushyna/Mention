package com.mention.service002;

import com.mention.model.Message;

import java.util.Optional;

public interface MessageService {

  Optional<Message> getMessage(Long id);

  void addMessage(Message message);

  void deleteMessage(Long id);

  void updateMessage(Message message);
}
