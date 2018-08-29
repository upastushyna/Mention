package com.mention.service;

import com.mention.dto.ChatRq;
import com.mention.dto.ChatRs;
import com.mention.model.Chat;
import com.mention.repository.ChatRepository;
import com.mention.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ChatServiceImpl implements ChatService {

  private ChatRepository chatRepository;

  private UserRepository userRepository;

  private ModelMapper modelMapper;

  @Autowired
  public ChatServiceImpl(ChatRepository chatRepository,
                         UserRepository userRepository) {
    this.chatRepository = chatRepository;
    this.userRepository = userRepository;
    this.modelMapper = new ModelMapper();
  }

  @Override
  public List<ChatRs> getChatsByUsername(String username) {
    Optional<List<Chat>> chats = chatRepository
        .findByUser1UsernameOrUser2Username(username, username);
    if (chats.isPresent()) {
      List<Chat> currentChats = chats.get();
      List<ChatRs> chatRs = currentChats.stream().map(chat ->
          modelMapper.map(chat, ChatRs.class))
          .collect(Collectors.toList());
      return chatRs;
    }
    return null;
  }

  @Override
  public ChatRs getChatByUsernames(String username1, String username2) {
    Optional<Chat> chat =
        chatRepository.findByUser1UsernameAndUser2UsernameOrUser2UsernameAndUser1Username(
            username1, username2, username1, username2);
    if (chat.isPresent()) {
      ChatRs currentChat = modelMapper.map(chat.get(), ChatRs.class);
      return currentChat;
    }
    return null;
  }

  @Override
  @Transactional
  public void addChat(ChatRq chat) {
    Chat insertChat = modelMapper.map(chat, Chat.class);
    String username1 = chat.getUser1().getUsername();
    String username2 = chat.getUser2().getUsername();
    if (!chatRepository.findByUser1UsernameAndUser2UsernameOrUser2UsernameAndUser1Username(
        username1, username2, username1, username2
    ).isPresent() && userRepository.findByUsername(username2).isPresent()) {
      insertChat.setUser1(userRepository.findByUsername(username1).get());
      insertChat.setUser2(userRepository.findByUsername(username2).get());
      chatRepository.save(insertChat);
    }
  }
}
