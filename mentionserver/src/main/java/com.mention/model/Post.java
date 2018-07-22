package com.mention.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
public class Post {
  @Id
  @Column(name = "post_id")
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  private Long id;

  @Column(nullable = false, name = "post_body")
  private String body;

  @ManyToOne
  @JoinColumn(name = "user_id", nullable = false)
  @JsonIgnoreProperties(value = "posts")
  private User author;

  @Column(name = "post_timestamp")
  private Timestamp timestamp;

  /*
    @Column
    private List<T> post_likes;
  */

  @Column(name = "post_mediafile_url")
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

  public User getAuthor() {
    return author;
  }

  public void setAuthor(User author) {
    this.author = author;
  }

  public Timestamp getTimestamp() {
    return timestamp;
  }

  public void setTimestamp(Timestamp timestamp) {
    this.timestamp = timestamp;
  }

  public Object getMediafileUrl() {
    return mediafileUrl;
  }

  public void setMediafileUrl(String mediafileUrl) {
    this.mediafileUrl = mediafileUrl;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (!(obj instanceof Post)) {
      return false;
    }
    Post post = (Post) obj;
    return Objects.equals(id, post.id)
        &&
          Objects.equals(body, post.body)
        &&
          Objects.equals(author, post.author)
        &&
          Objects.equals(timestamp, post.timestamp)
        &&
          Objects.equals(mediafileUrl, post.mediafileUrl);
  }

  @Override
  public int hashCode() {

    return Objects.hash(id, body, author, timestamp, mediafileUrl);
  }
}
