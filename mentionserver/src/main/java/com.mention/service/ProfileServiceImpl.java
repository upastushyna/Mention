package com.mention.service;

import com.mention.dto.ProfileRq;
import com.mention.dto.ProfileRs;
import com.mention.model.Profile;
import com.mention.model.User;
import com.mention.repository.ProfileRepository;
import com.mention.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class ProfileServiceImpl implements ProfileService {

  private ProfileRepository profileRepository;


  @Autowired
  public ProfileServiceImpl(ProfileRepository profileRepository) {
    this.profileRepository = profileRepository;
  }

  @Override
  @Transactional
  public void addProfile(ProfileRq profile) {
    ModelMapper modelMapper = new ModelMapper();
    Profile newProfile = modelMapper.map(profile, Profile.class);
    profileRepository.save(newProfile);
  }

  @Override
  @Transactional
  public void updateProfile(ProfileRq profile) {
    ModelMapper modelMapper = new ModelMapper();
    Profile updatedProfile = modelMapper.map(profile, Profile.class);
    profileRepository.save(updatedProfile);
  }

  @Override
  public ProfileRs getProfileById(Long id) {
    ModelMapper modelMapper = new ModelMapper();
    Profile profile = profileRepository.findByUserId(id);
    return modelMapper.map(profile, ProfileRs.class);
  }
}


