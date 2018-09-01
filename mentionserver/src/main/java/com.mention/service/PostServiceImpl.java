package com.mention.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.mention.config.AmazonS3Configuration;
import com.mention.dto.ApiRs;
import com.mention.dto.PostIdRq;
import com.mention.dto.PostRq;
import com.mention.dto.PostRs;
import com.mention.model.Follow;
import com.mention.model.Post;
import com.mention.model.User;
import com.mention.repository.PostRepository;
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
  public ResponseEntity<?> addPost(String body, Long userId, MultipartFile file) throws IOException {
    UserPrincipal userPrincipal = (UserPrincipal) SecurityContextHolder
        .getContext()
        .getAuthentication()
        .getPrincipal();
    if (!userId.equals(userPrincipal.getId())) {
      return new ResponseEntity(new ApiRs(false, "Access denied"), HttpStatus.FORBIDDEN);
    }

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
      String url = s3.getUrl(bucket, key).toString();
      post.setMediaFileUrl(url);
      post.setAmazonKey(key);
    }
    postRepository.save(post);
    return ResponseEntity.ok(new ApiRs(true, "Post added successfully"));
  }

  @Override
  @Transactional
  public ResponseEntity<?> rePost(PostRq post) {
    UserPrincipal userPrincipal = (UserPrincipal) SecurityContextHolder
        .getContext()
        .getAuthentication()
        .getPrincipal();
    if (!post.getAuthor().getId().equals(userPrincipal.getId())) {
      return new ResponseEntity(new ApiRs(false, "Access denied"), HttpStatus.FORBIDDEN);
    }

    Post insertPost = modelMapper.map(post, Post.class);
    postRepository.save(insertPost);
    return ResponseEntity.ok(new ApiRs(true, "Reposted successfully"));
  }

  @Override
  @Transactional
  public ResponseEntity<?> deletePost(PostIdRq postDtoIdRq) {
    Post post = postRepository.findById(postDtoIdRq.getId()).get();
    UserPrincipal userPrincipal = (UserPrincipal) SecurityContextHolder
        .getContext()
        .getAuthentication()
        .getPrincipal();
    if (!post.getAuthor().getId().equals(userPrincipal.getId())) {
      return new ResponseEntity(new ApiRs(false, "Access denied"), HttpStatus.FORBIDDEN);
    }

    Post currentPost = postRepository.findById(postDtoIdRq.getId()).get();
    AmazonS3 s3 = as3.getAmazonS3();
    if (currentPost.getAmazonKey() != null) {
      String oldKey = currentPost.getAmazonKey();
      s3.deleteObject(bucket, oldKey);
    }

    postRepository.deleteById(postDtoIdRq.getId());
    return ResponseEntity.ok(new ApiRs(true, "Deleted successfully"));
  }

 /* public void deletePost(Long postId) {
    postRepository.deleteById(postId);
  }*/
}