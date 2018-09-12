package com.mention.service;

import com.mention.dto.NotificationIdRq;
import org.springframework.http.ResponseEntity;

public interface NotificationService {

  ResponseEntity<?> setChecked (NotificationIdRq notification);

  ResponseEntity<?> setAllChecked();

  ResponseEntity<?> getUnread ();

  ResponseEntity<?> getAllForUser();
}
