package com.mention.service;

import com.mention.dto.MessageRq;
import org.springframework.http.ResponseEntity;

public interface MessageService {

  ResponseEntity<?> addMessage(MessageRq message);
}
