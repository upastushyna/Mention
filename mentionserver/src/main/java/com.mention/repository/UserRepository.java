package com.mention.repository;

import com.mention.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

  Optional<User> findByUsername(String username);

  User findUserByEmail(String email);

  void deleteByUsername(String username);

  List<User> findByEmail(String email);

  List<User> findByUsernameContainingIgnoreCase(String username);

  Optional<User> findByUsernameOrEmail(String username, String email);

}
