package org.danit.mention.service;

import org.danit.mention.dto.LoginDetailsRq;
import org.danit.mention.model.User;
import org.danit.mention.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthServiceImpl implements AuthService {

  private UserRepository userRepository;
  static Logger log = LoggerFactory.getLogger(AuthServiceImpl.class);

  @Autowired
  public AuthServiceImpl(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public boolean login(LoginDetailsRq loginDetailsRq) {
    Optional<User> user = userRepository.findByUsername(loginDetailsRq.getUsername());
    log.info("__" + user.toString());
    if (user.isPresent()) {
      return user.get().getPassword().equals(loginDetailsRq.getPassword());
    } else {
      return false;
    }
  }
}
