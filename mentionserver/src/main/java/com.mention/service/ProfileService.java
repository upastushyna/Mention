package com.mention.service;

import com.mention.dto.ProfileRq;
import com.mention.dto.ProfileRs;
import org.springframework.http.ResponseEntity;

public interface ProfileService {

  void addProfile(ProfileRq profile);

  ResponseEntity<?> updateProfile(ProfileRq profile);

  ResponseEntity<?> getProfileById(Long id);

}
