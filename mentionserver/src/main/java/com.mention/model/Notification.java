package com.mention.model;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@Entity
@Data
@EntityListeners(AuditingEntityListener.class)
public class Notification {

  @Id
  @Column(name = "notification_id")
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  private Long id;

  @Column(name = "notification_url", updatable = false, nullable = false)
  private String url;

  @Column(name = "notification_type", updatable = false, nullable = false)
  private String type;

  @Column(name = "notification_is_checked", nullable = false)
  private boolean isChecked;

  @ManyToOne
  @JoinColumn(name = "user_id", updatable = false, nullable = false)
  private User user;

  @CreatedDate
  @Temporal(TemporalType.TIMESTAMP)
  @Column(nullable = false, name = "notification_timestamp", updatable = false)
  private Date timestamp;

  @LastModifiedDate
  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "notification_modify_timestamp")
  private Date modifyTimestamp;

  public Notification(String url, String type, User user) {
    this.url = url;
    this.type = type;
    this.user = user;
  }
}
