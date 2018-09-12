package com.mention.service;

import com.mention.config.Constants;
import com.mention.dto.ApiRs;
import com.mention.dto.NotificationPopRs;
import com.mention.dto.PostLikeRq;
import com.mention.model.Notification;
import com.mention.model.Post;
import com.mention.model.PostLike;
import com.mention.model.User;
import com.mention.repository.NotificationRepository;
import com.mention.repository.PostLikeRepository;
import com.mention.repository.PostRepository;
import com.mention.security.UserPrincipal;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PostLikeServiceImpl implements PostLikeService {

  private PostLikeRepository postLikeRepository;

  private NotificationRepository notificationRepository;

  private SimpMessagingTemplate template;

  private PostRepository postRepository;

  private ModelMapper modelMapper;

  @Autowired
  public PostLikeServiceImpl(PostLikeRepository postLikeRepository,
                             NotificationRepository notificationRepository,
                             SimpMessagingTemplate template,
                             PostRepository postRepository) {
    this.postLikeRepository = postLikeRepository;
    this.notificationRepository = notificationRepository;
    this.template = template;
    this.postRepository = postRepository;
    this.modelMapper = new ModelMapper();
  }

  @Override
  @Transactional
  public ResponseEntity<?> addPostLike(PostLikeRq postLike) {
    UserPrincipal userPrincipal = UserPrincipal.getPrincipal();
    if (!postLike.getUser().getId().equals(userPrincipal.getId())) {
      return new ResponseEntity(new ApiRs(false, "Access denied"), HttpStatus.FORBIDDEN);
    }

    PostLike insertPostLike = modelMapper.map(postLike, PostLike.class);
    postLikeRepository.save(insertPostLike);

    Post post = postRepository.findById(postLike.getPost().getId()).get();

    if (userPrincipal.getId().equals(post.getAuthor().getId())) {
      return ResponseEntity.ok(new ApiRs(true, "Liked successfully"));
    }

    Notification notification = new Notification(
        Constants.POST_LIKE,
        userPrincipal.getUser(),
        post.getAuthor());
    notification.setPost(post);
    notificationRepository.save(notification);

    template.convertAndSendToUser(post.getAuthor().getUsername(),
        Constants.WS_NOTIFY, modelMapper.map(notification, NotificationPopRs.class));

    return ResponseEntity.ok(new ApiRs(true, "Liked successfully"));
  }

  @Override
  @Transactional
  public ResponseEntity<?> deletePostLike(PostLikeRq postLike) {
    UserPrincipal userPrincipal = UserPrincipal.getPrincipal();
    if (!postLike.getUser().getId().equals(userPrincipal.getId())) {
      return new ResponseEntity(new ApiRs(false, "Access denied"), HttpStatus.FORBIDDEN);
    }

    postLikeRepository.deleteByUserIdAndPostId(
        postLike.getUser().getId(),
        postLike.getPost().getId());
    return ResponseEntity.ok(new ApiRs(true, "Like removed successfully"));
  }


}
