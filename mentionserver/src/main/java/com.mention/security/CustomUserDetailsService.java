package com.mention.security;

import com.mention.model.User;
import com.mention.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

  @Autowired
  UserRepository userRepository;

  @Override
  @Transactional
  public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
    Optional<User> user = userRepository.findByUsernameOrEmail(usernameOrEmail, usernameOrEmail);
    if (user.isPresent()) {
      return UserPrincipal.create(user.get());
    }
    throw new UsernameNotFoundException("User not found with username or email : " + usernameOrEmail);
  }

  @Transactional
  public UserDetails loadUserById(Long id) {
    Optional<User> user = userRepository.findById(id);
    if (user.isPresent()) {
      return UserPrincipal.create(user.get());
    }
    throw new UsernameNotFoundException("User not found with id : " + id);
  }

}
