package com.mention.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
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
@EntityListeners(AuditingEntityListener.class)
public class Comment {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  @Column(name = "comment_id")
  private Long id;

  @Column(nullable = false, name = "comment_body")
  private String body;

  @ManyToOne
  @JoinColumn(name = "user_id", nullable = false)
  @JsonIgnoreProperties(value = "comments")
  private User commentator;

  @ManyToOne
  @JoinColumn(name = "post_id", nullable = false)
  @JsonIgnoreProperties(value = "comments")
  private Post post;

  @CreationTimestamp
  @Temporal(TemporalType.TIMESTAMP)
  @Column(nullable = false, name = "comment_timestamp")
  private Date timestamp;

  public Date getModifyTimestamp() {
    return modifyTimestamp;
  }

  public void setModifyTimestamp(Date modifyTimestamp) {
    this.modifyTimestamp = modifyTimestamp;
  }

  @UpdateTimestamp
  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "comment_modify_timestamp")
  private Date modifyTimestamp;

  @Column(name = "post_mediafileurl")
  private String mediafileUrl;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getBody() {
    return body;
  }

  public void setBody(String body) {
    this.body = body;
  }

  public User getCommentator() {
    return commentator;
  }

  public void setCommentator(User commentator) {
    this.commentator = commentator;
  }

  public Post getPost() {
    return post;
  }

  public void setPost(Post post) {
    this.post = post;
  }

  public Date getTimestamp() {
    return timestamp;
  }

  public void setTimestamp(Date timestamp) {
    this.timestamp = timestamp;
  }

  public String getMediafileUrl() {
    return mediafileUrl;
  }

  public void setMediafileUrl(String mediafileUrl) {
    this.mediafileUrl = mediafileUrl;
  }
}
