package com.mention.service;

import com.mention.dto.ChatDtoRs;
import java.util.List;

public interface UserChatsService {

  List<ChatDtoRs> getChatsByUsername(String username);
}
