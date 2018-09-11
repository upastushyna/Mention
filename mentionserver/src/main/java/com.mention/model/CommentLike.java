package com.mention.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import java.util.Date;

@Entity
@Data
@Table(name = "commentLikes", uniqueConstraints = @UniqueConstraint(columnNames = {"user_id", "comment_id"}))
@EntityListeners(AuditingEntityListener.class)
public class CommentLike {

  @Id
  @Column(name = "comment_like_id")
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "user_id", updatable = false)
  @JsonIgnoreProperties(value = {"profile", "postLikes", "commentLikes", "posts", "comments", "chats", "favorites"})
  private User user;

  @ManyToOne
  @JoinColumn(name = "comment_id", updatable = false)
  @JsonIgnoreProperties(value = {"commentator", "commentLikes"})
  private Comment comment;

  @CreatedDate
  @Temporal(TemporalType.TIMESTAMP)
  @Column(nullable = false, name = "comment_timestamp", updatable = false)
  private Date timestamp;

  protected CommentLike() {
  }

  public CommentLike(User user, Comment comment) {
    this.user = user;
    this.comment = comment;
  }
}


