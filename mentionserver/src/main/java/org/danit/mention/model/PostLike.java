package org.danit.mention.model;

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
import java.util.Date;

@Data
@Entity
@Table(name = "postLikes")
@EntityListeners(AuditingEntityListener.class)
public class PostLike {

  @Id
  @Column(name = "post_like_id")
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  private Long id;


  @ManyToOne
  @JoinColumn(name = "user_id", updatable = false)
  @JsonIgnoreProperties(value = {"profile", "postLikes", "posts", "comments", "chats", "favorites"})
  private User user;


  @ManyToOne
  @JoinColumn(name = "post_id", updatable = false)
  @JsonIgnoreProperties(value = {"author", "postLikes", "comments", "favorites"})
  private Post post;

  @CreatedDate
  @Temporal(TemporalType.TIMESTAMP)
  @Column(nullable = false, name = "post_timestamp", updatable = false)
  private Date timestamp;

  protected PostLike() {
  }

  public PostLike(User user, Post post) {
    this.user = user;
    this.post = post;
  }
}

