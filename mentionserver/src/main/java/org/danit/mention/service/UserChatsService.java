package org.danit.mention.service;

import org.danit.mention.dto.ChatDtoRs;

import java.util.List;

public interface UserChatsService {

  List<ChatDtoRs> getChatsByUsername(String username);

  ChatDtoRs getChatByUsernames(String username1, String username2);
}
