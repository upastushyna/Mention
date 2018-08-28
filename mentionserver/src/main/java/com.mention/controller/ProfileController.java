package com.mention.controller;

import com.mention.dto.ProfileDtoRq;
import com.mention.dto.ProfileDtoRs;
import com.mention.dto.ShortUserDetailsRs;
import com.mention.model.Profile;
import com.mention.service.UserProfileServiceImpl;
import com.mention.service.UserService;
import com.mention.service.UserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/profile")
public class ProfileController {

  private UserProfileServiceImpl userProfileService;

  @Autowired
  public ProfileController(UserProfileServiceImpl userProfileService) {
    this.userProfileService = userProfileService;
  }

  @PostMapping
  public void addProfile(@RequestBody ProfileDtoRq profile) {
    userProfileService.addProfile(profile);
  }

  @GetMapping("/{id}")
  public ProfileDtoRs getUser(@PathVariable Long id) {
    return userProfileService.getProfileById(id);
  }

  @PutMapping
  public void updateUser(@RequestBody ProfileDtoRq profile) {
    userProfileService.updateProfile(profile);
  }

  @DeleteMapping("/{id}")
  public void deleteProfile(@PathVariable Long id) {
    userProfileService.deleteProfile(id);
  }
}
