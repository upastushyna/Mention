package com.mention.service;

import com.mention.dto.ProfileDtoRq;
import com.mention.dto.ProfileDtoRs;

public interface UserProfileService {


  void addProfile(ProfileDtoRq profile);

  void updateProfile(ProfileDtoRq profile);

  ProfileDtoRs getProfileByUserName(String username);

  void deleteProfile(Long id);
}
