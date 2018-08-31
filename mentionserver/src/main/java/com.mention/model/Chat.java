package com.mention.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import java.util.Date;
import java.util.List;

@Entity
@Data
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"user1_id", "user2_id"}))
@EntityListeners(AuditingEntityListener.class)
public class Chat {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  @Column(name = "chat_id")
  private Long id;

  @ManyToOne
  @JoinColumn(name = "user1_id", nullable = false, updatable = false)
  @JsonIgnoreProperties(value = {"profile", "posts", "comments", "chats", "favorites"})
  private User user1;

  @ManyToOne
  @JoinColumn(name = "user2_id", nullable = false, updatable = false)
  @JsonIgnoreProperties(value = {"profile", "posts", "comments", "chats", "favorites"})
  private User user2;

  @OneToMany(cascade = CascadeType.ALL, mappedBy = "chat")
  @JsonIgnoreProperties(value = {"chat"})
  private List<Message> messages;

  @CreatedDate
  @Temporal(TemporalType.TIMESTAMP)
  @Column(nullable = false, name = "chat_timestamp", updatable = false)
  private Date timestamp;

  @LastModifiedDate
  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "chat_modify_timestamp")
  private Date modifyTimestamp;

  protected Chat(){}

  public Chat(User user1, User user2) {
    this.user1 = user1;
    this.user2 = user2;
  }
}
