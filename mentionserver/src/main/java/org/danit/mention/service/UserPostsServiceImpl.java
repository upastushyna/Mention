package org.danit.mention.service;

import org.danit.mention.dto.PostDtoRs;
import org.danit.mention.model.Follow;
import org.danit.mention.model.Post;
import org.danit.mention.model.User;
import org.danit.mention.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserPostsServiceImpl implements UserPostsService {

  private UserRepository userRepository;

  @Autowired
  public UserPostsServiceImpl(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public List<PostDtoRs> getFollowedPosts(String username) {
    ModelMapper modelMapper = new ModelMapper();
    Optional<User> currentUser = userRepository.findByUsername(username);
    if (currentUser.isPresent()) {
      User user = currentUser.get();
      List<Post> posts = new ArrayList<>();
      for (Follow followed:
           user.getFollowedUsers()) {
        posts.addAll(followed.getFollowedUser().getPosts());
      }
      List<PostDtoRs> postDtoRs = posts.stream().map(post -> modelMapper.map(
          post, PostDtoRs.class)).collect(Collectors.toList());
      return postDtoRs;
    }
    return null;
  }

  @Override
  public List<PostDtoRs> getPostsByUsername(String username) {
    ModelMapper modelMapper = new ModelMapper();
    Optional<User> currentUser = userRepository.findByUsername(username);
    if (currentUser.isPresent()) {
      List<PostDtoRs> postDtoRs = currentUser.get().getPosts().stream().map(
          post -> modelMapper.map(post, PostDtoRs.class)).collect(Collectors.toList());
      return postDtoRs;
    }
    return null;
  }
}
