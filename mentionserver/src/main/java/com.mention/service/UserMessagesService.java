package com.mention.service;

import com.mention.dto.MessageDtoRq;

public interface UserMessagesService {

  void addMessage(MessageDtoRq message);
}
