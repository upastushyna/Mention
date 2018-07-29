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

@Entity
@Data
public class Likes {

  @Id
  @Column(name = "like_id")
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "user_id", updatable = false)
  @JsonIgnoreProperties(value = {"posts", "sentMessages", "receivedMessages", "comments", "favorites", "chats"})
  private User user;

  @ManyToOne
  @JoinColumn(name = "post_id", updatable = false)
  @JsonIgnoreProperties(value = {"posts", "sentMessages", "receivedMessages", "comments", "favorites", "chats"})
  private Post post;
}
