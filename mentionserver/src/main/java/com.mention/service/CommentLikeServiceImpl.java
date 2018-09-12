package com.mention.service;

import com.mention.config.Constants;
import com.mention.dto.ApiRs;
import com.mention.dto.CommentLikeRq;
import com.mention.dto.NotificationPopRs;
import com.mention.model.Comment;
import com.mention.model.CommentLike;
import com.mention.model.Notification;
import com.mention.model.User;
import com.mention.repository.CommentLikeRepository;
import com.mention.repository.CommentRepository;
import com.mention.repository.NotificationRepository;
import com.mention.security.UserPrincipal;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CommentLikeServiceImpl implements CommentLikeService {

  private CommentLikeRepository commentLikeRepository;

  private CommentRepository commentRepository;

  private NotificationRepository notificationRepository;

  private ModelMapper modelMapper;

  private SimpMessagingTemplate template;

  @Autowired
  public CommentLikeServiceImpl(CommentLikeRepository commentLikeRepository,
                                CommentRepository commentRepository,
                                NotificationRepository notificationRepository,
                                SimpMessagingTemplate template) {
    this.commentLikeRepository = commentLikeRepository;
    this.commentRepository = commentRepository;
    this.notificationRepository = notificationRepository;
    this.template = template;
    this.modelMapper = new ModelMapper();
  }

  @Override
  @Transactional
  public ResponseEntity<?> addCommentLike(CommentLikeRq commentLikeDto) {
    UserPrincipal userPrincipal = UserPrincipal.getPrincipal();
    if (!commentLikeDto.getUser().getId().equals(userPrincipal.getId())) {
      return new ResponseEntity(new ApiRs(false, "Access denied"), HttpStatus.FORBIDDEN);
    }
    CommentLike insertCommentLike = modelMapper.map(commentLikeDto, CommentLike.class);
    commentLikeRepository.save(insertCommentLike);

    Comment comment = commentRepository.findById(insertCommentLike.getComment().getId()).get();
    if (comment.getCommentator().getId().equals(userPrincipal.getId())) {
      return ResponseEntity.ok(new ApiRs(true, "Liked successfully"));
    }
    User receiver = comment.getCommentator();
    Notification notification = new Notification(
        Constants.COMMENT_LIKE,
        userPrincipal.getUser(), receiver);
    notification.setPost(comment.getPost());
    notificationRepository.save(notification);

    template.convertAndSendToUser(receiver.getUsername(),
        Constants.WS_NOTIFY, modelMapper.map(notification, NotificationPopRs.class));

    return ResponseEntity.ok(new ApiRs(true, "Liked successfully"));
  }

  @Override
  @Transactional
  public ResponseEntity<?> deleteCommentLike(CommentLikeRq commentLikeDto) {
    UserPrincipal userPrincipal = UserPrincipal.getPrincipal();
    if (!commentLikeDto.getUser().getId().equals(userPrincipal.getId())) {
      return new ResponseEntity(new ApiRs(false, "Access denied"), HttpStatus.FORBIDDEN);
    }

    commentLikeRepository.deleteByUserIdAndCommentId(
        commentLikeDto.getUser().getId(),
        commentLikeDto.getComment().getId());
    return ResponseEntity.ok(new ApiRs(true, "Like removed successfully"));

  }
}
