package com.mention.service;

import com.mention.dto.ApiRs;
import com.mention.dto.ProfileRq;
import com.mention.dto.ProfileRs;
import com.mention.model.Profile;
import com.mention.model.User;
import com.mention.repository.ProfileRepository;
import com.mention.repository.UserRepository;
import com.mention.security.UserPrincipal;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
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
  public ResponseEntity<?> updateProfile(ProfileRq profile) {
    UserPrincipal userPrincipal = (UserPrincipal) SecurityContextHolder
        .getContext()
        .getAuthentication()
        .getPrincipal();
    if (!profile.getUser().getId().equals(userPrincipal.getId())) {
      return new ResponseEntity(new ApiRs(false, "Access denied"), HttpStatus.FORBIDDEN);
    }

    ModelMapper modelMapper = new ModelMapper();
    Profile updatedProfile = modelMapper.map(profile, Profile.class);
    profileRepository.save(updatedProfile);
    return ResponseEntity.ok(new ApiRs(true, "Profile updated successfully"));
  }

  @Override
  public ResponseEntity<?> getProfileById(Long id) {
    UserPrincipal userPrincipal = (UserPrincipal) SecurityContextHolder
        .getContext()
        .getAuthentication()
        .getPrincipal();
    if (!id.equals(userPrincipal.getId())) {
      return new ResponseEntity(new ApiRs(false, "Access denied"), HttpStatus.FORBIDDEN);
    }

    ModelMapper modelMapper = new ModelMapper();
    Profile profile = profileRepository.findByUserId(id);
    ProfileRs currentProfile =  modelMapper.map(profile, ProfileRs.class);
    return ResponseEntity.ok(currentProfile);
  }
}


