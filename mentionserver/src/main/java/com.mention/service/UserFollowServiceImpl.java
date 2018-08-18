package com.mention.service;

import com.mention.dto.FollowDtoRq;
import com.mention.dto.ShortUserDetailsRs;
import com.mention.dto.UserDtoIdRq;
import com.mention.dto.UserDtoRq;
import com.mention.model.Follow;
import com.mention.model.User;
import com.mention.repository.FollowRepository;
import com.mention.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserFollowServiceImpl implements UserFollowService {

  private UserRepository userRepository;
  private FollowRepository followRepository;
  private ModelMapper modelMapper;

  @Autowired
  public UserFollowServiceImpl(UserRepository userRepository, FollowRepository followRepository) {
    this.userRepository = userRepository;
    this.followRepository = followRepository;
    this.modelMapper = new ModelMapper();
  }

  @Override
  public List<ShortUserDetailsRs> getFollowedUsers(String username) {
    Optional<User> user = userRepository.findByUsername(username);
    if (user.isPresent()) {
      List<ShortUserDetailsRs> followedUsers =
          user.get().getFollowedUsers().stream()
              .map(follow -> modelMapper.map(follow.getFollowedUser(), ShortUserDetailsRs.class))
          .collect(Collectors.toList());
      return followedUsers;
    }
    return null;
  }

  @Override
  public List<ShortUserDetailsRs> getFollowingUsers(String username) {
    Optional<User> user = userRepository.findByUsername(username);
    if (user.isPresent()) {
      List<ShortUserDetailsRs> followingUsers =
          user.get().getFollowers().stream()
              .map(follow -> modelMapper.map(follow.getFollower(), ShortUserDetailsRs.class))
          .collect(Collectors.toList());
      return followingUsers;
    }
    return null;
  }

  @Override
  @Transactional
  public void addFollow(FollowDtoRq follow) {
    Follow insertFollow = modelMapper.map(follow, Follow.class);
    followRepository.save(insertFollow);
  }

  @Override
  @Transactional
  public void deleteFollow(FollowDtoRq follow) {
    Follow deleteFollow = modelMapper.map(follow, Follow.class);
    followRepository.deleteByFollowerAndFollowedUser(
        deleteFollow.getFollower(),
        deleteFollow.getFollowedUser());
  }
}