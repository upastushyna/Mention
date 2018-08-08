package org.danit.mention.controller;

import org.danit.mention.model.User;
import org.danit.mention.service002.UserService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/user")
public class UserController {

  private UserService userService;

  public UserController(UserService userService) {
    this.userService = userService;
  }

  @PostMapping
  public void addUser(@RequestBody User user) {
    userService.addUser(user);
  }

  @GetMapping(value = "/{id}")
  public Optional<User> getUser(@PathVariable Long id) {
    return userService.getUser(id);
  }

  @PutMapping
  public void updateUser(@RequestBody User user) {
    userService.updateUser(user);
  }

  @DeleteMapping(value = "/{id}")
  public void deleteUser(@PathVariable Long id) {
    userService.deleteUser(id);
  }

}
