package com.mention.service;

import com.mention.config.Constants;
import com.mention.dto.ApiRs;
import com.mention.dto.CommentIdRq;
import com.mention.dto.CommentRq;
import com.mention.dto.NotificationPopRs;
import com.mention.model.Comment;
import com.mention.model.Notification;
import com.mention.model.Post;
import com.mention.model.User;
import com.mention.repository.CommentRepository;
import com.mention.repository.NotificationRepository;
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
public class CommentServiceImpl implements CommentService {

  private CommentRepository commentRepository;

  private PostRepository postRepository;

  private NotificationRepository notificationRepository;

  private SimpMessagingTemplate template;

  private ModelMapper modelMapper;

  @Autowired
  public CommentServiceImpl(CommentRepository commentRepository,
                            PostRepository postRepository,
                            NotificationRepository notificationRepository,
                            SimpMessagingTemplate template) {
    this.commentRepository = commentRepository;
    this.postRepository = postRepository;
    this.notificationRepository = notificationRepository;
    this.template = template;
    this.modelMapper = new ModelMapper();
  }

  @Override
  public ResponseEntity<?> addComment(CommentRq comment) {
    UserPrincipal userPrincipal = UserPrincipal.getPrincipal();
    if (!comment.getCommentator().getId().equals(userPrincipal.getId())) {
      return new ResponseEntity(new ApiRs(false, "Access denied"), HttpStatus.FORBIDDEN);
    }

    Comment insertComment = modelMapper.map(comment, Comment.class);
    commentRepository.save(insertComment);
    Post post = postRepository.findById(comment.getPost().getId()).get();

    if (!userPrincipal.getId().equals(post.getAuthor().getId())) {
      Notification notification = new Notification(Constants.FRONT_NOTIFY,
          Constants.COMMENT,
          userPrincipal.getUser(), post.getAuthor());
      notificationRepository.save(notification);

      template.convertAndSendToUser(post.getAuthor().getUsername(),
          Constants.WS_NOTIFY, modelMapper.map(notification, NotificationPopRs.class));
    }
    return ResponseEntity.ok(new ApiRs(true, "Comment added succesfully"));
  }

  @Override
  @Transactional
  public ResponseEntity<?> deleteComment(CommentIdRq comment) {
    Comment deletedComment = commentRepository.findById(comment.getId()).get();
    UserPrincipal userPrincipal = UserPrincipal.getPrincipal();
    if (!deletedComment.getCommentator().getId().equals(userPrincipal.getId())) {
      return new ResponseEntity(new ApiRs(false, "Access denied"), HttpStatus.FORBIDDEN);
    }

    commentRepository.deleteById(comment.getId());
    return ResponseEntity.ok(new ApiRs(true, "Deleted successfully"));
  }


}
