package com.mention.dao;

import com.mention.model.Message;
import org.springframework.data.repository.CrudRepository;

public interface MessageDao extends CrudRepository<Message, Long> {

  /*Message getMessage(Long id);

  void addMessage(Message message);

  void deleteMessage(Long id);

  void updateMessage(Message message);*/

}
