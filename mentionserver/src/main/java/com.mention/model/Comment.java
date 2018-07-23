package com.mention.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.sql.Timestamp;


@Entity
public class Comment {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  private Long id;

  @Column(nullable = false)
  private String commentBody;

  @ManyToOne
  @JoinColumn(name = "user_id")
  private User commentator;

  @ManyToOne
  @JoinColumn(name = "post_id")
  private Post post;

  @Column(name = "post_timestamp")
  private Timestamp timestamp;

  @Column(name = "post_medifileurl")
  private String mediafileUrl;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getCommentBody() {
    return commentBody;
  }

  public void setCommentBody(String commentBody) {
    this.commentBody = commentBody;
  }

  public User getCommentCreator() {
    return commentator;
  }

  public void setCommentCreator(User commentCreator) {
    this.commentator = commentCreator;
  }

  public Post getCommentedPost() {
    return post;
  }

  public void setCommentedPost(Post commentedPost) {
    this.post = commentedPost;
  }

  public Timestamp getTimestamp() {
    return timestamp;
  }

  public void setTimestamp(Timestamp timestamp) {
    this.timestamp = timestamp;
  }

  public String getMediafileUrl() {
    return mediafileUrl;
  }

  public void setMediafileUrl(String mediafileUrl) {
    this.mediafileUrl = mediafileUrl;
  }
}
