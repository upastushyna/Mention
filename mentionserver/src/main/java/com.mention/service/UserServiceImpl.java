package com.mention.service;

import com.mention.dao.UserDao;
import com.mention.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

  private UserDao userDao;

  @Autowired
  public UserServiceImpl(UserDao userDao) {
    this.userDao = userDao;
  }

  @Override
  @Transactional
  public void addUser(User user) {
    user.setActive(true);
    userDao.save(user);
  }

  @Override
  public Optional<User> getUser(Long id) {
    return userDao.findById(id);
  }

  @Override
  @Transactional
  public void updateUser(User user) {
    userDao.save(user);
  }

  @Override
  @Transactional
  public void deleteUser(Long id) {
    User user = userDao.findById(id).get();
    user.setActive(false);
    userDao.save(user);
  }
}
