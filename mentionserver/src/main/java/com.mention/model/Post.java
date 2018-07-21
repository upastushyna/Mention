package com.mention.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Post {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  private Long post_id;

  @Column(nullable = false)
  private String post_body;

  @Column
  private User post_author;

  @Column
  private Long post_timestamp;

  /*
    @Column
    private List<T> post_likes;
  */

  @Column
  private String post_mediafile;

  @Column
  private String post_comment;

  public boolean isPost_isActive() {
    return post_isActive;
  }

  public void setPost_isActive(boolean post_isActive) {
    this.post_isActive = post_isActive;
  }

  @Column(nullable = false)
  private boolean post_isActive;

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

  public Long getPost_timestamp() {
    return post_timestamp;
  }

  public void setPost_timestamp(Long post_timestamp) {
    this.post_timestamp = post_timestamp;
  }

  public Object getPost_mediafile() {
    return post_mediafile;
  }

  public void setPost_mediafile(String post_mediafile) {
    this.post_mediafile = post_mediafile;
  }

  public String getPost_comment() {
    return post_comment;
  }

  public void setPost_comment(String post_comment) {
    this.post_comment = post_comment;
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
          Objects.equals(post_mediafile, post.post_mediafile)
        &&
          Objects.equals(post_comment, post.post_comment);
  }

  @Override
  public int hashCode() {

    return Objects.hash(post_id, post_body, post_author, post_timestamp, post_mediafile, post_comment);
  }
}
