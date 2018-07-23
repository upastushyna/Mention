package com.mention.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;

@Entity
public class Post {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  private Long post_id;

  @Column(nullable = false)
  private String post_body;

  @ManyToOne
  @JoinColumn(name = "USER_ID")
  @JsonIgnoreProperties(value = "user_posts")
  private User post_author;

  @OneToMany(mappedBy = "commentator")
  private List<Comment> comments;

  @Column
  private Timestamp post_timestamp;

  /*
    @Column
    private List<T> post_likes;
  */

  @Column
  private String post_mediafileUrl;

  public Long getPost_id() {
    return post_id;
  }

  public void setPost_id(Long post_id) {
    this.post_id = post_id;
  }

  public String getPost_body() {
    return post_body;
  }

  public void setPost_body(String post_body) {
    this.post_body = post_body;
  }

  public User getPost_author() {
    return post_author;
  }

  public void setPost_author(User post_author) {
    this.post_author = post_author;
  }

  public Timestamp getPost_timestamp() {
    return post_timestamp;
  }

  public void setPost_timestamp(Timestamp post_timestamp) {
    this.post_timestamp = post_timestamp;
  }

  public Object getPost_mediafileUrl() {
    return post_mediafileUrl;
  }

  public void setPost_mediafileUrl(String post_mediafileUrl) {
    this.post_mediafileUrl = post_mediafileUrl;
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
    return Objects.equals(post_id, post.post_id)
        &&
          Objects.equals(post_body, post.post_body)
        &&
          Objects.equals(post_author, post.post_author)
        &&
          Objects.equals(post_timestamp, post.post_timestamp)
        &&
          Objects.equals(post_mediafileUrl, post.post_mediafileUrl);
  }

  @Override
  public int hashCode() {

    return Objects.hash(post_id, post_body, post_author, post_timestamp, post_mediafileUrl);
  }
}
