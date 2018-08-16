package com.mention.controller;

import com.mention.dto.ShortUserDetailsRs;
import com.mention.dto.UserDtoIdRq;
import com.mention.dto.UserDtoRq;
import com.mention.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {

  private UserServiceImpl userService;

  @Autowired
  public UserController(UserServiceImpl userService) {
    this.userService = userService;
  }

  @GetMapping("/{username}")
  public ShortUserDetailsRs getUser(@PathVariable String username) {
    return userService.getUser(username);
  }

  @GetMapping("/search/{username}")
  public List<ShortUserDetailsRs> getUsersByUsername(@PathVariable String username) {
    return userService.getUsersByUsername(username.replace("%20", " "));
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
