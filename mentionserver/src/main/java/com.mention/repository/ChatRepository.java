package com.mention.repository;

import com.mention.model.Chat;
import com.mention.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ChatRepository extends JpaRepository<Chat, Long> {

  Optional<List<Chat>> findByUser1UsernameOrUser2Username(String username1, String username2);

  Optional<Chat> findByUser1UsernameAndUser2UsernameOrUser2UsernameAndUser1Username(
      String username1, String username2, String username3, String username4);

  Optional<Chat> findByUser1IdAndUser2IdOrUser2IdAndUser1Id(Long id1, Long id2, Long id3, Long id4);
}
