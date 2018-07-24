package com.mention.service;

import com.mention.dao.UserDao;
import com.mention.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

  private UserDao userDao;

  @Autowired
  public UserServiceImpl(UserDao userDao) {
    this.userDao = userDao;
  }

  @Override
  public void addUser(User user) {
    user.setActive(true);
    userDao.save(user);
  }

  @Override
  public Optional<User> getUser(Long id) {
    return userDao.findById(id);
  }

  @Override
  public void updateUser(User user) {
    userDao.save(user);
  }

  @Override
  public void deleteUser(Long id) {
    User user = userDao.findById(id).get();
    user.setActive(false);
    userDao.save(user);
  }
}
