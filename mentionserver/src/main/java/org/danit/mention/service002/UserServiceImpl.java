package org.danit.mention.service002;

import org.danit.mention.repository.UserRepository;
import org.danit.mention.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

  private UserRepository userRepository;

  @Autowired
  public UserServiceImpl(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  @Transactional
  public void addUser(User user) {
    user.setActive(true);
    userRepository.save(user);
  }

  @Override
  public Optional<User> getUser(Long id) {
    return userRepository.findById(id);
  }

  @Override
  @Transactional
  public void updateUser(User user) {
    userRepository.save(user);
  }

  @Override
  @Transactional
  public void deleteUser(Long id) {
    User user = userRepository.findById(id).get();
    user.setActive(false);
    userRepository.save(user);
  }
}
