package com.mention.repository;

import com.mention.model.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {

  List<Notification> findAllByReceiverIdAndIsChecked(Long id, boolean isChecked);

  List<Notification> findAllByReceiverId(Long id);
}