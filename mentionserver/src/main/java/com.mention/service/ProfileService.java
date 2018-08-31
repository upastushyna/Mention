package com.mention.service;

import com.mention.dto.ProfileRq;
import com.mention.dto.ProfileRs;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ProfileService {

  void addProfile(ProfileRq profile);

  ResponseEntity<?> updateProfile(ProfileRq profile);

  ResponseEntity<?> getProfileById(Long id);

  ResponseEntity<?> updateAvatar(MultipartFile file) throws IOException;

  ResponseEntity<?> updateBackground(MultipartFile file) throws IOException;

}
