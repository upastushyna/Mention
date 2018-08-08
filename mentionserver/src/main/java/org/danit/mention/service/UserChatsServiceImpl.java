package org.danit.mention.service;

import org.danit.mention.dto.ChatDtoRs;
import org.danit.mention.model.Chat;
import org.danit.mention.repository.ChatRepository;
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
        .findByUser1_UsernameOrUser2_Username(username, username);
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
        chatRepository.findByUser1_UsernameAndUser2_UsernameOrUser2_UsernameAndUser1_Username(
            username1, username2, username1, username2);
    if (chat.isPresent()) {
      ChatDtoRs currentChat = modelMapper.map(chat.get(), ChatDtoRs.class);
      return currentChat;
    }
    return null;
  }
}
