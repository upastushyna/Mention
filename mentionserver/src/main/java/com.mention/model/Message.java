package com.mention.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
public class Message {

  @Id
  @Column(name = "message_id")
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  private Long id;

  @Column(nullable = false, name = "message_content")
  private String content;

  @ManyToOne
  @JoinColumn(name = "sender_id", nullable = false, updatable = false)
  @JsonIgnoreProperties(value = {"sentMessages", "posts", "comments", "receivedMessages", "chats", "favorites"})
  private User sender;

  @ManyToOne
  @JoinColumn(name = "receiver_id", nullable = false, updatable = false)
  @JsonIgnoreProperties(value = {"sentMessages", "posts", "comments", "receivedMessages", "chats", "favorites"})
  private User receiver;

  @CreatedDate
  @Temporal(TemporalType.TIMESTAMP)
  @Column(nullable = false, name = "message_timestamp", updatable = false)
  private Date timestamp;

  @LastModifiedDate
  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "message_modify_timestamp")
  private Date modifyTimestamp;

}
