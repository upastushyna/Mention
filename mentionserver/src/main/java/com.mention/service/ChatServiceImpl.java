package com.mention.service;

import com.mention.dao.ChatDao;
import com.mention.model.Chat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ChatServiceImpl implements ChatService {

  private ChatDao chatDao;

  @Autowired
  public ChatServiceImpl(ChatDao chatDao) {
    this.chatDao = chatDao;
  }

  @Override
  public Optional<Chat> getChat(Long id) {
    return chatDao.findById(id);
  }

  @Override
  public void addChat(Chat chat) {
    chatDao.save(chat);
  }

  @Override
  public void deleteChat(Long id) {
    chatDao.deleteById(id);
  }
}
