package com.mention.controller;

import com.mention.dto.UserDtoIdRq;
import com.mention.dto.UserDtoNewUserRq;
import com.mention.service.UserNewUserImpl;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/registration")
public class UserCreateUserController {

  private UserNewUserImpl userNewUser;

  public UserCreateUserController(UserNewUserImpl userNewUser) {
    this.userNewUser = userNewUser;
  }

  @PostMapping("/add")
  public void createUser(@RequestBody UserDtoNewUserRq userDtoNewUser) {
    userNewUser.createNewUser(userDtoNewUser);
  }

  @DeleteMapping("/delete")
  public  void deleteUser(@RequestBody UserDtoIdRq userDtoIdRq) {
    userNewUser.deleteUser(userDtoIdRq);
  }
}
