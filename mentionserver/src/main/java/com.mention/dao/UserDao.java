package com.mention.dao;

import com.mention.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserDao extends CrudRepository<User, Long> {

  /*void addUser(User user);

  User getUser(Long id);

  void updateUser(User user);

  void deleteUser(Long id);*/
}
