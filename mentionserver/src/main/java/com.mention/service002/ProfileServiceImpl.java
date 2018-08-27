package com.mention.service002;

import com.mention.repository.ProfileRepository;
import com.mention.model.Profile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class ProfileServiceImpl implements ProfileService{

  private ProfileRepository profileRepository;


  @Autowired
  public ProfileServiceImpl(ProfileRepository profileRepository) {
    this.profileRepository = profileRepository;
  }

  @Override
  public void addProfile(Profile profile) {
    profileRepository.save(profile);
  }

  @Override
  public Optional<Profile> getProfile(Long id) {
    return profileRepository.findById(id);
  }

  @Override
  @Transactional
  public void updateProfile(Profile profile) {
    profileRepository.save(profile);
  }

  @Override
  public void deleteProfile(Long id) {
    profileRepository.deleteById(id);
  }
}
