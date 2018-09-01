package com.mention.controller;

import com.mention.dto.ProfileRq;
import com.mention.dto.ProfileRs;
import com.mention.service.ProfileServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/profiles")
public class ProfileController {

  private ProfileServiceImpl userProfileService;

  @Autowired
  public ProfileController(ProfileServiceImpl userProfileService) {
    this.userProfileService = userProfileService;
  }

  @PostMapping("/add")
  public void addProfile(@RequestBody ProfileRq profile) {
    userProfileService.addProfile(profile);
  }

  @GetMapping("/{id}")
  public ResponseEntity<?> getProfileById(@PathVariable Long id) {
    return userProfileService.getProfileById(id);
  }

  @PutMapping("/update")
  public ResponseEntity<?> updateProfile(@RequestBody ProfileRq profile) {
    return userProfileService.updateProfile(profile);
  }

  @PutMapping("/avatar")
  public ResponseEntity<?> updateAvatar(@RequestParam(value = "image") MultipartFile file)
      throws IOException {
    return userProfileService.updateAvatar(file);
  }

  @PutMapping("/background")
  public ResponseEntity<?> updateBackground(@RequestParam(value = "image") MultipartFile file)
      throws IOException {
    return userProfileService.updateBackground(file);
  }
}
