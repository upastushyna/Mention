package com.mention.controller;

import com.mention.model.User;
import com.mention.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
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
@RequestMapping("/user")
public class UserController {

  @Autowired
  UserService userService;

  @PostMapping
  @Transactional
  public void addUser(@RequestBody User user) {
    userService.addUser(user);
  }

  @GetMapping(value = "/{id}")
  public Optional<User> getUser(@PathVariable Long id) {
    return userService.getUser(id);
  }

  @PutMapping
  @Transactional
  public void updateUser(@RequestBody User user) {
    userService.updateUser(user);
  }

  @DeleteMapping(value = "/{id}")
  @Transactional
  public void deleteUser(@PathVariable Long id) {
    userService.deleteUser(id);
  }

}
