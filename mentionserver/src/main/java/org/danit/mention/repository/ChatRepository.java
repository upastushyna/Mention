package org.danit.mention.repository;

import org.danit.mention.model.Chat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ChatRepository extends JpaRepository<Chat, Long> {

  Optional<List<Chat>> findByUser1_UsernameOrUser2_Username(String username1, String username2);

  Optional<Chat> findByUser1_UsernameAndUser2_UsernameOrUser2_UsernameAndUser1_Username(
      String username1, String username2, String username3, String username4);
}
