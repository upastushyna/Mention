package com.mention.dao;

import com.mention.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
public class UserDaoImpl implements UserDao {

  @Autowired
  private EntityManager entityManager;


  @Override
  public void addUser(User user) {
    entityManager.persist(user);
  }

  @Override
  public User getUser(Long id) {
    return entityManager.find(User.class, id);
  }

  @Override
  public void updateUser(User user) {
    entityManager.merge(user);
  }

  @Override
  public void deleteUser(Long id) {
    User user = entityManager.find(User.class, id);
    user.setActive(false);
    entityManager.merge(user);
  }
}
