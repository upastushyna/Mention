package com.mention.repository;

import com.mention.model.Chat;
import com.mention.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ChatRepository extends JpaRepository<Chat, Long> {

  Optional<List<Chat>> findByUser1_UsernameOrUser2_Username(String username1, String username2);

}
