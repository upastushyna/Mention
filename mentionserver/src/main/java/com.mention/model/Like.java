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
public class Like {

  @Id
  @Column(name = "like_id")
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "user_id", nullable = false, updatable = false)
  @JsonIgnoreProperties(value = {"posts", "sentMessages", "receivedMessages", "comments", "favorites", "chats"})
  private User liked;

  @ManyToOne
  @JoinColumn(name = "post_id", nullable = false, updatable = false)
  @JsonIgnoreProperties(value = {"posts", "sentMessages", "receivedMessages", "comments", "favorites", "chats"})
  private Post post;

  @CreatedDate
  @Temporal(TemporalType.TIMESTAMP)
  @Column(nullable = false, name = "comment_timestamp", updatable = false)
  private Date timestamp;

  @LastModifiedDate
  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "comment_modify_timestamp")
  private Date modifyTimestamp;
}
