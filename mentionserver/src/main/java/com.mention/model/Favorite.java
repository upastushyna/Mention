package com.mention.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Data
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"user_id", "post_id"}))
public class Favorite {
  @Id
  @Column(name = "favorite_id")
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "user_id", nullable = false, updatable = false)
  @JsonIgnoreProperties(value = {"profile", "posts", "comments", "chats", "favorites"})
  private User user;

  @ManyToOne
  @JoinColumn(name = "post_id", nullable = false, updatable = false)
  @JsonIgnoreProperties(value = {"author", "comments", "favorites"})
  private Post post;
}
