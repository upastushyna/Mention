package com.mention.service;

import com.mention.dto.ShortUserDetailsRs;
import com.mention.dto.UserDtoIdRq;
import com.mention.dto.UserDtoRq;

public interface UserService {

  void createNewUser(UserDtoRq userDtoNewUser);

  void deleteUser(UserDtoIdRq user);

  ShortUserDetailsRs getUser(String username);
}
