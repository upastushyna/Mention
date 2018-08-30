package com.mention.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.mention.config.AmazonS3Configuration;
import com.mention.dto.PostIdRq;
import com.mention.dto.PostRq;
import com.mention.dto.PostRs;
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
public class PostServiceImpl implements PostService {

  final String bucket = AmazonS3Configuration.BUCKET_NAME;

  private UserRepository userRepository;

  private PostRepository postRepository;

  private AmazonS3Configuration as3;

  private ModelMapper modelMapper;

  @Autowired
  public PostServiceImpl(UserRepository userRepository,
                              PostRepository postRepository,
                              AmazonS3Configuration as3) {
    this.userRepository = userRepository;
    this.postRepository = postRepository;
    this.modelMapper = new ModelMapper();
    this.as3 = as3;
  }

  @Override
  public List<PostRs> getFollowedPosts(String username) {
    Optional<User> currentUser = userRepository.findByUsername(username);
    if (currentUser.isPresent()) {
      User user = currentUser.get();
      List<Post> posts = new ArrayList<>();
      for (Follow followed :
          user.getFollowedUsers()) {
        posts.addAll(followed.getFollowedUser().getPosts());
      }
      List<PostRs> postRs = posts.stream().map(post -> modelMapper.map(
          post, PostRs.class))
          .sorted((p1, p2) -> p2.getTimestamp().compareTo(p1.getTimestamp()))
          .collect(Collectors.toList());
      return postRs;
    }
    return null;
  }

  @Override
  public List<PostRs> getPostsByUsername(String username) {
    Optional<User> currentUser = userRepository.findByUsername(username);
    if (currentUser.isPresent()) {
      List<PostRs> postRs = currentUser.get().getPosts().stream().map(
          post -> modelMapper.map(post, PostRs.class))
          .sorted((p1, p2) -> p2.getTimestamp().compareTo(p1.getTimestamp()))
          .collect(Collectors.toList());
      return postRs;
    }
    return null;
  }

  @Override
  public List<PostRs> getLikedPosts(String username) {
    Optional<User> currentUser = userRepository.findByUsername(username);
    if (currentUser.isPresent()) {
      List<PostRs> likedPosts = currentUser.get()
          .getPostLikes().stream()
          .map(postLike -> modelMapper.map(postLike.getPost(), PostRs.class))
          .collect(Collectors.toList());
      return likedPosts;
    }
    return null;
  }

  @Override
  public List<PostRs> getPostsByBody(String body) {
    List<Post> posts = postRepository.findByBodyContainingIgnoreCase(body);
    if (!posts.isEmpty()) {
      List<PostRs> currentPosts = posts.stream()
          .map(post -> modelMapper.map(post, PostRs.class))
          .collect(Collectors.toList());
      return currentPosts;
    }
    return null;
  }

  @Override
  @Transactional
  public void addPost(String body, Long userId, MultipartFile file) throws IOException {
    AmazonS3 s3 = as3.getAmazonS3();
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
  public void rePost(PostRq post) {
    Post insertPost = modelMapper.map(post, Post.class);
    postRepository.save(insertPost);
  }

  @Override
  @Transactional
  public void updatePost(PostRq post) {
    Post updatedPost = modelMapper.map(post, Post.class);
    postRepository.save(updatedPost);
  }



  @Override
  @Transactional
  public void deletePost(Long postId) {
    postRepository.deleteById(postId);
  }
}