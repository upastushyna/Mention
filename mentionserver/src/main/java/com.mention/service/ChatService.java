package com.mention.service;

import com.mention.model.Chat;
import java.util.Optional;

public interface ChatService {

  Optional<Chat> getChat(Long id);

  void addChat(Chat chat);

  void deleteChat(Long id);

}
