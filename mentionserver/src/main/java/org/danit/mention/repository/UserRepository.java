package org.danit.mention.repository;

import org.danit.mention.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

  Optional<User> findByUsername(String username);

  User findUserByEmail(String email);

  Long deleteByUsername(String username);

}
