package com.mention.service;

import com.mention.dto.ChatDtoRq;
import com.mention.dto.ChatDtoRs;
import com.mention.model.Chat;
import com.mention.model.User;
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
public class UserChatsServiceImpl implements UserChatsService {

  private ChatRepository chatRepository;

  private UserRepository userRepository;

  private ModelMapper modelMapper;

  @Autowired
  public UserChatsServiceImpl(ChatRepository chatRepository,
                              UserRepository userRepository) {
    this.chatRepository = chatRepository;
    this.userRepository = userRepository;
    this.modelMapper = new ModelMapper();
  }

  @Override
  public List<ChatDtoRs> getChatsByUsername(String username) {
    Optional<List<Chat>> chats = chatRepository
        .findByUser1UsernameOrUser2Username(username, username);
    if (chats.isPresent()) {
      List<Chat> currentChats = chats.get();
      List<ChatDtoRs> chatDtoRs = currentChats.stream().map(chat ->
          modelMapper.map(chat, ChatDtoRs.class))/*.
          sorted((c1, c2) -> c2.getMessages().get(c2.getMessages().size()-1).getTimestamp()
          .compareTo(c1.getMessages().get(c1.getMessages().size()-1)
              .getTimestamp()))*/.collect(Collectors.toList());
      return chatDtoRs;
    }
    return null;
  }

  @Override
  public ChatDtoRs getChatByUsernames(String username1, String username2) {
    Optional<Chat> chat =
        chatRepository.findByUser1UsernameAndUser2UsernameOrUser2UsernameAndUser1Username(
            username1, username2, username1, username2);
    if (chat.isPresent()) {
      ChatDtoRs currentChat = modelMapper.map(chat.get(), ChatDtoRs.class);
      return currentChat;
    }
    return null;
  }

  @Override
  @Transactional
  public void addChat(ChatDtoRq chat) {
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
