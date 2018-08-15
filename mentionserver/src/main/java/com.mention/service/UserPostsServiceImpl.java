package com.mention.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.mention.config.AmazonS3Configuration;
import com.mention.dto.PostDtoIdRq;
import com.mention.dto.PostDtoRq;
import com.mention.dto.PostDtoRs;
import com.mention.dto.UserDtoIdRq;
import com.mention.model.Follow;
import com.mention.model.Post;
import com.mention.model.User;
import com.mention.repository.PostRepository;
import com.mention.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserPostsServiceImpl implements UserPostsService {

  final AmazonS3 s3 = AmazonS3Configuration.S3_BUILDER;
  final String bucket = AmazonS3Configuration.BUCKET_NAME;

  private UserRepository userRepository;

  private PostRepository postRepository;

  private ModelMapper modelMapper;

  @Autowired
  public UserPostsServiceImpl(UserRepository userRepository, PostRepository postRepository) {
    this.userRepository = userRepository;
    this.postRepository = postRepository;
    this.modelMapper = new ModelMapper();
  }

  @Override
  public List<PostDtoRs> getFollowedPosts(String username) {
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
  public List<PostDtoRs> getLikedPosts(String username) {
    Optional<User> currentUser = userRepository.findByUsername(username);
    if (currentUser.isPresent()) {
      List<PostDtoRs> likedPosts = currentUser.get()
          .getPostLikes().stream()
          .map(postLike -> modelMapper.map(postLike.getPost(), PostDtoRs.class))
          .collect(Collectors.toList());
      return likedPosts;
    }
    return null;
  }

  @Override
  @Transactional
  public void addPost(String body, Long userId, MultipartFile file) throws IOException {
    User user = new User(userId);
    Post post = new Post(body, user);
    if (file != null) {
      String key = "pictures/" + file.getOriginalFilename();
      InputStream myFile = file.getInputStream();
      s3.putObject(
          bucket,
          key,
          myFile,
          new ObjectMetadata());
      String url = s3.getUrl(bucket,key).toString();
      post.setMediaFileUrl(url);
      post.setAmazonKey(key);
    }
    postRepository.save(post);
  }

  @Override
  @Transactional
  public void rePost(PostDtoRq post) {
    Post insertPost = modelMapper.map(post, Post.class);
    postRepository.save(insertPost);
  }

  @Override
  @Transactional
  public void updatePost(PostDtoRq post) {
    Post updatedPost = modelMapper.map(post, Post.class);
    postRepository.save(updatedPost);
  }

  @Override
  @Transactional
  public void deletePost(PostDtoIdRq postDtoIdRq) {
    Post deletedPost = modelMapper.map(postDtoIdRq, Post.class);
    postRepository.deleteById(
        deletedPost.getId()
    );
  }
}
