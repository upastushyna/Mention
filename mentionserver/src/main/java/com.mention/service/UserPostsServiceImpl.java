package com.mention.service;

import com.mention.dto.PostDto;
import com.mention.dto.PostUserDetails;
import com.mention.model.Follow;
import com.mention.model.Post;
import com.mention.model.User;
import com.mention.repository.UserRepository;
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
  public List<PostDto> getFollowedPosts(Long id) {
    ModelMapper modelMapper = new ModelMapper();
    Optional<User> currentUser = userRepository.findById(id);
    if (currentUser.isPresent()) {
      User user = currentUser.get();
      List<Post> posts = new ArrayList<>();
      for (Follow followed:
           user.getFollowedUsers()) {
        posts.addAll(followed.getFollowedUser().getPosts());
      }
      List<PostDto> postDtos = posts.stream().map(post -> modelMapper.map(
          post, PostDto.class)).collect(Collectors.toList());
      return postDtos;

    }
    return null;
  }
}
