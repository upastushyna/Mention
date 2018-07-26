package com.mention.service;

import com.mention.model.Profile;

import java.util.Optional;

public interface ProfileService {

  void addProfile(Profile profile);

  Optional<Profile> getProfile(Long id);

  void updateProfile(Profile profile);

  void deletePrifile(Long id);

}
