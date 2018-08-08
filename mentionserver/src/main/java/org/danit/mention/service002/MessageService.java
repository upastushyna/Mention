package org.danit.mention.service002;

import org.danit.mention.model.Message;

import java.util.Optional;

public interface MessageService {

  Optional<Message> getMessage(Long id);

  void addMessage(Message message);

  void deleteMessage(Long id);

  void updateMessage(Message message);
}
