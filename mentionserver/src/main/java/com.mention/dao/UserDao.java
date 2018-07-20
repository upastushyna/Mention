package com.mention.dao;

import com.mention.model.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao {

  void addUser(User user);

  User getUser(Long id);

  void updateUser(User user);

  void deleteUser(Long id);
}
