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
public class PostComment {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  @Column(name = "post_comment_id")
  private Long id;

  @Column(nullable = false, name = "post_comment_body")
  private String body;

  @ManyToOne
  @JoinColumn(name = "user_id", nullable = false, updatable = false)
  @JsonIgnoreProperties(value = {"profile", "posts", "postComments", "chats", "favorites"})
  private User commentator;

  @ManyToOne
  @JoinColumn(name = "post_id", nullable = false, updatable = false)
  @JsonIgnoreProperties(value = {"author", "postComments", "favorites"})
  private Post post;

  @CreatedDate
  @Temporal(TemporalType.TIMESTAMP)
  @Column(nullable = false, name = "post_comment_timestamp", updatable = false)
  private Date timestamp;

  @LastModifiedDate
  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "post_comment_modify_timestamp")
  private Date modifyTimestamp;

  @Column(name = "post_comment_mediafileurl")
  private String mediafileUrl;

  protected PostComment(){}

  public PostComment(String body, User commentator, Post post) {
    this.body = body;
    this.commentator = commentator;
    this.post = post;
  }
}