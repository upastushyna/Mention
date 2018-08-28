package com.mention.service;

import com.mention.dto.ProfileRq;
import com.mention.dto.ProfileRs;

public interface ProfileService {


  void addProfile(ProfileRq profile);

  void updateProfile(ProfileRq profile);

  ProfileRs getProfileByUserName(String username);

}
