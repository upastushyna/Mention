package com.mention.service;

import com.mention.dto.ProfileDtoRq;
import com.mention.dto.ProfileDtoRs;
import com.mention.dto.UserDtoUsernameRq;

public interface UserProfileService {


  void addProfile(ProfileDtoRq profile);

  void updateProfile(ProfileDtoRq profile);

  /* ProfileDtoRs getProfileByUserName(String username);*/
}
