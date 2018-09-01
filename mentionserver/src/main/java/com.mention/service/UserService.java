package com.mention.service;

import com.mention.dto.CurrentUserRs;
import com.mention.dto.ShortUserDetailsRs;
import com.mention.dto.UserIdRq;
import com.mention.dto.UserRq;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserService {

  void createNewUser(UserRq userDtoNewUser);

  ResponseEntity<?> deleteUser(UserIdRq user);

  ShortUserDetailsRs getUser(String username);

  List<ShortUserDetailsRs> getUsersByUsername(String username);

  CurrentUserRs getCurrentUser();
}
