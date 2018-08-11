package com.mention.service;

import com.mention.dto.ChatDtoRs;
import com.mention.model.Chat;
import com.mention.repository.ChatRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserChatsServiceImpl implements UserChatsService {

  private ChatRepository chatRepository;

  @Autowired
  public UserChatsServiceImpl(ChatRepository chatRepository) {
    this.chatRepository = chatRepository;
  }

  @Override
  public List<ChatDtoRs> getChatsByUsername(String username) {
    ModelMapper modelMapper = new ModelMapper();
    Optional<List<Chat>> chats = chatRepository
        .findByUser1UsernameOrUser2Username(username, username);
    if (chats.isPresent()) {
      List<Chat> currentChats = chats.get();
      List<ChatDtoRs> chatDtoRs = currentChats.stream().map(chat ->
          modelMapper.map(chat, ChatDtoRs.class)).collect(Collectors.toList());
      return chatDtoRs;
    }
    return null;
  }

  @Override
  public ChatDtoRs getChatByUsernames(String username1, String username2) {
    ModelMapper modelMapper = new ModelMapper();
    Optional<Chat> chat =
        chatRepository.findByUser1UsernameAndUser2UsernameOrUser2UsernameAndUser1Username(
            username1, username2, username1, username2);
    if (chat.isPresent()) {
      ChatDtoRs currentChat = modelMapper.map(chat.get(), ChatDtoRs.class);
      return currentChat;
    }
    return null;
  }
}
