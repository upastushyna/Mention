package com.mention.security;

import com.mention.model.User;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class UserPrincipal implements UserDetails {

  private Long id;

  private String username;

  private String email;

  private String password;

  private List<? extends GrantedAuthority> authorities;

  public UserPrincipal(Long id,
                       String username,
                       String email,
                       String password,
                       List<? extends GrantedAuthority> authorities) {
    this.id = id;
    this.username = username;
    this.email = email;
    this.password = password;
    this.authorities = authorities;
  }

  public static UserPrincipal create(User user) {

    return new UserPrincipal(
        user.getId(),
        user.getUsername(),
        user.getEmail(),
        user.getPassword(),
        user.getRoles().stream().map(s -> (GrantedAuthority) () -> s).collect(Collectors.toList())
    );
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return authorities;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }
}
