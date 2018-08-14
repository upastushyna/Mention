package com.mention.controller;

import com.mention.dto.ProfileDtoRq;
import com.mention.service.UserProfileServiceImpl;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/profile")
public class UserProfileController {

  private UserProfileServiceImpl userProfileService;

  public UserProfileController(UserProfileServiceImpl userProfileService) {
    this.userProfileService = userProfileService;
  }

  @PostMapping("/add")
  public void addProfile(@RequestBody ProfileDtoRq profile) {
    userProfileService.addProfile(profile);
  }

  @PutMapping("/update")
  public void updateProfile(@RequestBody ProfileDtoRq profile) {
    userProfileService.updateProfile(profile);
  }

}
