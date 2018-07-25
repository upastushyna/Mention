package com.mention.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Data
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"user1_id", "user2_id"}))
public class Chat {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  @Column(name = "chat_id")
  private Long id;

  @ManyToOne
  @JoinColumn(name = "user1_id", nullable = false, updatable = false)
  @JsonIgnoreProperties(value = {"sentMessages", "posts", "comments", "receivedMessages", "chats"})
  private User user1;

  @ManyToOne
  @JoinColumn(name = "user2_id", nullable = false, updatable = false)
  @JsonIgnoreProperties(value = {"sentMessages", "posts", "comments", "receivedMessages", "chats"})
  private User user2;

}
