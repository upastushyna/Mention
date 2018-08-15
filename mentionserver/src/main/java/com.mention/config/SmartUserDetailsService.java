package com.mention.config;

import com.mention.model.User;
import com.mention.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class SmartUserDetailsService implements UserDetailsService {
  @Autowired
  private UserRepository userRepository;

  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    List<User> byEmail = userRepository.findByEmail(email);
    if (byEmail.size() != 1) {
      throw new UsernameNotFoundException(String.format("User with login `%s` not found", email));
    }
    User u = byEmail.get(0);
    return new org.springframework.security.core.userdetails.
        User(
        u.getEmail(), u.getPassword(), getAuthorities(u.getRoles())
    );
  }

  private static List<GrantedAuthority> getAuthorities (List<String> roles) {
    List<GrantedAuthority> authorities = new ArrayList<>();
    for (String role : roles) {
      authorities.add(new SimpleGrantedAuthority(role));
    }
    return authorities;
  }

}
