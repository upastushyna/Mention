package com.mention.service;

import com.mention.dto.ProfileDtoRq;
import com.mention.dto.ProfileDtoRs;
import com.mention.dto.UserDtoUsernameRq;
import com.mention.model.Profile;
import com.mention.model.User;
import com.mention.repository.ProfileRepository;
import com.mention.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UserProfileServiceImpl implements UserProfileService {

  private ProfileRepository profileRepository;

  private UserRepository userRepository;

  @Autowired
  public UserProfileServiceImpl(ProfileRepository profileRepository, UserRepository userRepository) {
    this.profileRepository = profileRepository;
    this.userRepository = userRepository;
  }

  @Override
  @Transactional
  public void addProfile(ProfileDtoRq profile) {
    ModelMapper modelMapper = new ModelMapper();
    Profile newProfile = modelMapper.map(profile, Profile.class);
    profileRepository.save(newProfile);
  }

  @Override
  @Transactional
  public void updateProfile(ProfileDtoRq profile) {
    ModelMapper modelMapper = new ModelMapper();
    Profile updatedProfile = modelMapper.map(profile, Profile.class);
    profileRepository.save(updatedProfile);
  }

  @Override
  public ProfileDtoRs getProfileByUserName(String username) {
    ModelMapper modelMapper = new ModelMapper();
    Optional<User> currentUser = userRepository.findByUsername(username);
    if (currentUser.isPresent()) {

      Profile currentProfile = currentUser.get().getProfile();
      return modelMapper.map(currentProfile, ProfileDtoRs.class);
    }
    return null;
  }

}


