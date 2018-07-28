package com.mention.service;

import com.mention.dao.ProfileDao;
import com.mention.model.Profile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProfileServiceImpl implements ProfileService{

  private ProfileDao profileDao;


  @Autowired
  public ProfileServiceImpl(ProfileDao profileDao) {
    this.profileDao = profileDao;
  }

  @Override
  public void addProfile(Profile profile) {
    profileDao.save(profile);
  }

  @Override
  public Optional<Profile> getProfile(Long id) {
    return profileDao.findById(id);
  }

  @Override
  public void updateProfile(Profile profile) {
    profileDao.save(profile);
  }

  @Override
  public void deletePrifile(Long id) {
    profileDao.deleteById(id);
  }
}
