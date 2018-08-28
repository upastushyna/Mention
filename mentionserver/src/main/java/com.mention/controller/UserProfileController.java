package com.mention.controller;

import com.mention.dto.ProfileDtoRq;
import com.mention.dto.ProfileDtoRs;
import com.mention.dto.ShortUserDetailsRs;
import com.mention.service.UserProfileServiceImpl;
import com.mention.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/profiles")
public class UserProfileController {

  private UserProfileServiceImpl userProfileService;
  private UserService userService;

  public UserProfileController(UserProfileServiceImpl userProfileService, UserService userService) {
    this.userProfileService = userProfileService;
    this.userService = userService;
  }

  @PostMapping("/add")
  public void addProfile(@RequestBody ProfileDtoRq profile) {
    userProfileService.addProfile(profile);
  }

  @GetMapping("/{id}")
  public ShortUserDetailsRs getUser(@PathVariable Long id) {
    return userService.getUser(id);
  }

  @PutMapping("/update")
  public void updateProfile(@RequestBody ProfileDtoRq profile) {
    userProfileService.updateProfile(profile);
  }

  @GetMapping("/{username}")
  public ProfileDtoRs getProfileByUsername(@PathVariable String username) {
    return userProfileService.getProfileByUserName(username);
  }

}
