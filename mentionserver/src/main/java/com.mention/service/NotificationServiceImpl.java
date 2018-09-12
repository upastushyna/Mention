package com.mention.service;

import com.mention.dto.ApiRs;
import com.mention.dto.NotificationIdRq;
import com.mention.dto.NotificationRs;
import com.mention.model.Notification;
import com.mention.repository.NotificationRepository;
import com.mention.security.UserPrincipal;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class NotificationServiceImpl implements NotificationService {

  private NotificationRepository notificationRepository;

  private ModelMapper modelMapper;

  @Autowired
  public NotificationServiceImpl(NotificationRepository notificationRepository) {
    this.notificationRepository = notificationRepository;
    this.modelMapper = new ModelMapper();
  }

  @Override
  @Transactional
  public ResponseEntity<?> setChecked(NotificationIdRq notification) {
    Optional<Notification> currentNotification = notificationRepository.findById(notification.getId());
    if (!currentNotification.isPresent()) {
      return new ResponseEntity(new ApiRs(false, "Not found"), HttpStatus.NOT_FOUND);
    }
    currentNotification.get().setChecked(true);
    notificationRepository.save(currentNotification.get());
    return ResponseEntity.ok(new ApiRs(true, "Updated successfully"));
  }

  @Override
  @Transactional
  public ResponseEntity<?> setAllChecked() {
    UserPrincipal userPrincipal = UserPrincipal.getPrincipal();
    List<Notification> notifications = notificationRepository
        .findAllByReceiverIdAndIsChecked(userPrincipal.getId(), false);
    notifications.forEach(notification -> notification.setChecked(true));
    notifications.forEach(notification -> notificationRepository.save(notification));
    return ResponseEntity.ok(new ApiRs(true, "Updated successfully"));
  }

  @Override
  public ResponseEntity<?> getUnread() {
    UserPrincipal userPrincipal = UserPrincipal.getPrincipal();
    List<Notification> notifications = notificationRepository
        .findAllByReceiverIdAndIsChecked(userPrincipal.getId(), false);
    List<NotificationRs> notificationRsList = notifications
        .stream()
        .map(notification -> modelMapper.map(notification, NotificationRs.class))
        .collect(Collectors.toList());
    return ResponseEntity.ok(notificationRsList);
  }

  @Override
  public ResponseEntity<?> getAllForUser() {
    UserPrincipal userPrincipal = UserPrincipal.getPrincipal();
    List<Notification> notifications = notificationRepository
        .findAllByReceiverId(userPrincipal.getId());
    List<NotificationRs> notificationRsList = notifications
        .stream()
        .map(notification -> modelMapper.map(notification, NotificationRs.class))
        .collect(Collectors.toList());
    return ResponseEntity.ok(notificationRsList);
  }
}
