package com.mention.service;

import com.mention.dto.UserDtoIdRq;
import com.mention.dto.UserDtoNewUserRq;

public interface UserNewUser {

  void createNewUser(UserDtoNewUserRq userDtoNewUser);

  void deleteUser(UserDtoIdRq user);

}
