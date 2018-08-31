package com.mention.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.mention.config.AmazonS3Configuration;
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
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;

@Service
public class ProfileServiceImpl implements ProfileService {

  final String bucket = AmazonS3Configuration.BUCKET_NAME;

  private ProfileRepository profileRepository;

  private AmazonS3Configuration as3;


  @Autowired
  public ProfileServiceImpl(ProfileRepository profileRepository,
                            AmazonS3Configuration as3) {
    this.profileRepository = profileRepository;
    this.as3 = as3;
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
  @Transactional
  public ResponseEntity<?> updateAvatar(MultipartFile file) throws IOException {
    UserPrincipal userPrincipal = (UserPrincipal) SecurityContextHolder
        .getContext()
        .getAuthentication()
        .getPrincipal();
    Profile profile = profileRepository.findByUserId(userPrincipal.getId());

    AmazonS3 s3 = as3.getAmazonS3();
    String key = "avatars/" + file.getOriginalFilename();
    InputStream myFile = file.getInputStream();
    s3.putObject(
        bucket,
        key,
        myFile,
        new ObjectMetadata());
    String url = s3.getUrl(bucket,key).toString();

    profile.setAvatarUrl(url);
    profile.setAvatarKey(key);
    profileRepository.save(profile);

    return ResponseEntity.ok(new ApiRs(true, "Avatar updated successfully"));
  }

  @Override
  @Transactional
  public ResponseEntity<?> updateBackground(MultipartFile file) throws IOException {
    UserPrincipal userPrincipal = (UserPrincipal) SecurityContextHolder
        .getContext()
        .getAuthentication()
        .getPrincipal();
    Profile profile = profileRepository.findByUserId(userPrincipal.getId());

    AmazonS3 s3 = as3.getAmazonS3();
    String key = "backgrounds/" + file.getOriginalFilename();
    InputStream myFile = file.getInputStream();
    s3.putObject(
        bucket,
        key,
        myFile,
        new ObjectMetadata());
    String url = s3.getUrl(bucket,key).toString();

    profile.setBackgroundUrl(url);
    profile.setBackgroundKey(key);

    profileRepository.save(profile);
    return ResponseEntity.ok(new ApiRs(true, "Background updated successfully"));
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


