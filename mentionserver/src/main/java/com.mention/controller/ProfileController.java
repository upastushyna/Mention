package com.mention.controller;

import com.mention.model.Profile;
import com.mention.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.nio.file.OpenOption;
import java.util.Optional;

@RestController
@RequestMapping("/profile")
public class ProfileController {

  private ProfileService profileService;

  @Autowired
  public ProfileController(ProfileService profileService) {
    this.profileService = profileService;
  }

  @PostMapping
  public void addProfile(@RequestBody Profile profile) {
    profileService.addProfile(profile);
  }

  @GetMapping("/{id}")
  public Optional<Profile> getProfile(@PathVariable Long id) {
    return profileService.getProfile(id);
  }

  @PutMapping
  public void updateUser(@RequestBody Profile profile) {
    profileService.updateProfile(profile);
  }

  @DeleteMapping("/{id}")
  public void deleteProfile(@PathVariable Long id) {
    profileService.deletePrifile(id);
  }
}
