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

  @Column(nullable = false, name = "comment_body")
  private String body;

  @ManyToOne
  @JoinColumn(name = "user_id", nullable = false)
  private User commentator;

  @ManyToOne
  @JoinColumn(name = "post_id", nullable = false)
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
