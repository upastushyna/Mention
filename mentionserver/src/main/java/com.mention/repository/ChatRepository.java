package com.mention.repository;

import com.mention.model.Chat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ChatRepository extends JpaRepository<Chat, Long> {

  Optional<Chat> findChatByUsers(String username1, String username2 );

}
