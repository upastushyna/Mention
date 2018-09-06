package com.mention.service;

import com.mention.dto.ApiRs;
import com.mention.dto.ChatRq;
import com.mention.dto.ChatRs;
import com.mention.model.Chat;
import com.mention.model.Message;
import com.mention.repository.ChatRepository;
import com.mention.repository.UserRepository;
import com.mention.security.UserPrincipal;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
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
  public ResponseEntity<?> getChatsForCurrentUser() {
    UserPrincipal userPrincipal = (UserPrincipal) SecurityContextHolder
        .getContext()
        .getAuthentication()
        .getPrincipal();

    Optional<List<Chat>> chats = chatRepository
        .findByUser1UsernameOrUser2Username(userPrincipal.getUsername(), userPrincipal.getUsername());
    if (chats.isPresent()) {
      List<Chat> currentChats = chats.get();
      for (Chat chat:
           currentChats) {
        if (chat.getMessages().size() > 0) {
          chat.setModifyTimestamp(chat.getMessages().get(chat.getMessages().size() - 1).getTimestamp());
        }
      }
      currentChats.forEach(chat -> chat.getMessages()
          .sort(Comparator.comparing(Message::getTimestamp)));
      List<ChatRs> chatRs = currentChats.stream().map(chat ->
          modelMapper.map(chat, ChatRs.class))
          .sorted((c1, c2) -> c2.getModifyTimestamp().compareTo(c1.getModifyTimestamp()))
          .collect(Collectors.toList());
      return ResponseEntity.ok(chatRs);
    }
    return new ResponseEntity(new ApiRs(false, "Bad request"), HttpStatus.BAD_REQUEST);
  }

  @Override
  public ResponseEntity<?> getChatByUsernames(String username1, String username2) {
    UserPrincipal userPrincipal = (UserPrincipal) SecurityContextHolder
        .getContext()
        .getAuthentication()
        .getPrincipal();
    if (!username1.equals(userPrincipal.getUsername()) 
        && !username2.equals(userPrincipal.getUsername())) {
      return new ResponseEntity(new ApiRs(false, "Access denied"), HttpStatus.FORBIDDEN);
    }

    Optional<Chat> chat =
        chatRepository.findByUser1UsernameAndUser2UsernameOrUser2UsernameAndUser1Username(
            username1, username2, username1, username2);
    if (chat.isPresent()) {
      chat.get().getMessages().sort(Comparator.comparing(Message::getTimestamp));
      ChatRs currentChat = modelMapper.map(chat.get(), ChatRs.class);
      return ResponseEntity.ok(currentChat);
    }
    return new ResponseEntity(new ApiRs(false, "Bad Request"), HttpStatus.BAD_REQUEST);
  }

  @Override
  @Transactional
  public ResponseEntity<?> addChat(ChatRq chat) {
    UserPrincipal userPrincipal = (UserPrincipal) SecurityContextHolder
        .getContext()
        .getAuthentication()
        .getPrincipal();
    if (!chat.getUser1().getUsername().equals(userPrincipal.getUsername()) 
        && !chat.getUser2().getUsername().equals(userPrincipal.getUsername())) {
      return new ResponseEntity(new ApiRs(false, "Access denied"), HttpStatus.FORBIDDEN);
    }

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
    return ResponseEntity.ok(new ApiRs(true, "Chat added successfully"));
  }
}
