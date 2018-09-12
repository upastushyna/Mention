package com.mention.controller;

import com.mention.dto.NotificationIdRq;
import com.mention.service.NotificationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.ws.Response;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {

  private NotificationServiceImpl notificationService;

  @Autowired
  public NotificationController(NotificationServiceImpl notificationService) {
    this.notificationService = notificationService;
  }

  @PutMapping("/update")
  public ResponseEntity<?> setChecked(NotificationIdRq notification) {
    return notificationService.setChecked(notification);
  }

  @PutMapping("updateAll")
  public ResponseEntity<?> setAllChecked() {
    return notificationService.setAllChecked();
  }

  @GetMapping("/unread")
  public ResponseEntity<?> getUnread() {
    return notificationService.getUnread();
  }

  @GetMapping
  public ResponseEntity<?> getAllForUser() {
    return notificationService.getAllForUser();
  }
}
