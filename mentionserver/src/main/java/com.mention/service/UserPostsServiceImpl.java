package com.mention.service;

import com.mention.dto.PostDtoIdRq;
import com.mention.dto.PostDtoRq;
import com.mention.dto.PostDtoRs;
import com.mention.model.Follow;
import com.mention.model.Post;
import com.mention.model.User;
import com.mention.repository.PostRepository;
import com.mention.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserPostsServiceImpl implements UserPostsService {

  private UserRepository userRepository;

  private PostRepository postRepository;

  @Autowired
  public UserPostsServiceImpl(UserRepository userRepository, PostRepository postRepository) {
    this.userRepository = userRepository;
    this.postRepository = postRepository;
  }

  @Override
  public List<PostDtoRs> getFollowedPosts(String username) {
    ModelMapper modelMapper = new ModelMapper();
    Optional<User> currentUser = userRepository.findByUsername(username);
    if (currentUser.isPresent()) {
      User user = currentUser.get();
      List<Post> posts = new ArrayList<>();
      for (Follow followed :
          user.getFollowedUsers()) {
        posts.addAll(followed.getFollowedUser().getPosts());
      }
      List<PostDtoRs> postDtoRs = posts.stream().map(post -> modelMapper.map(
          post, PostDtoRs.class))
          .sorted((p1, p2) -> p2.getTimestamp().compareTo(p1.getTimestamp()))
          .collect(Collectors.toList());
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
          post -> modelMapper.map(post, PostDtoRs.class))
          .sorted((p1, p2) -> p2.getTimestamp().compareTo(p1.getTimestamp()))
          .collect(Collectors.toList());
      return postDtoRs;
    }
    return null;
  }

  @Override
  @Transactional
  public void addPost(PostDtoRq post) {
    ModelMapper modelMapper = new ModelMapper();
    Post insertPost = modelMapper.map(post, Post.class);
    postRepository.save(insertPost);
  }

  @Override
  @Transactional
  public void updatePost(PostDtoRq post) {
    ModelMapper modelMapper = new ModelMapper();
    Post updatedPost = modelMapper.map(post, Post.class);
    postRepository.save(updatedPost);
  }

  @Override
  @Transactional
  public void deletePost(PostDtoIdRq postDtoIdRq) {
    ModelMapper modelMapper = new ModelMapper();
    Post deletedPost = modelMapper.map(postDtoIdRq, Post.class);
    postRepository.deleteById(
        deletedPost.getId()
    );
  }
}
