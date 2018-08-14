package com.mention.controller;

import com.mention.dto.UserDtoIdRq;
import com.mention.dto.UserDtoRq;
import com.mention.service.UserServiceImpl;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserController {

  private UserServiceImpl userService;

  public UserController(UserServiceImpl userService) {
    this.userService = userService;
  }

  @PostMapping("/add")
  public void createUser(@RequestBody UserDtoRq userDtoNewUser) {
    userService.createNewUser(userDtoNewUser);
  }

  @DeleteMapping("/delete")
  public  void deleteUser(@RequestBody UserDtoIdRq userDtoIdRq) {
    userService.deleteUser(userDtoIdRq);
  }
}
