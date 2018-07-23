package com.mention.service;

import com.mention.dao.UserDaoImpl;
import com.mention.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

  @Autowired
  UserDaoImpl userDao;

  @Override
  public void addUser(User user) {
    user.setActive(true);
    userDao.addUser(user);
  }

  @Override
  public User getUser(Long id) {
    return userDao.getUser(id);
  }

  @Override
  public void updateUser(User user) {
    userDao.updateUser(user);
  }

  @Override
  public void deleteUser(Long id) {
    userDao
        .deleteUser(id);
  }
}
