package org.danit.mention.service002;

import org.danit.mention.repository.ProfileRepository;
import org.danit.mention.model.Profile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
  public void updateProfile(Profile profile) {
    profileRepository.save(profile);
  }

  @Override
  public void deletePrifile(Long id) {
    profileRepository.deleteById(id);
  }
}
