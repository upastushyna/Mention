package com.mention.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity

public class Lyke {

  @Id
  @Column(name = "like_id")
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  private Long id;


  @ManyToOne
  @JoinColumn(name = "user_id", updatable = false)
  @JsonIgnoreProperties(value = {"profile", "lykes", "posts", "comments", "chats", "favorites"})
  private User user;


  @ManyToOne
  @JoinColumn(name = "post_id", updatable = false)
  @JsonIgnoreProperties(value = {"author", "lykes", "comments", "favorites"})
  private Post post;

  public Long getId() {
    return id;
  }

   public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public Post getPost() {
    return post;
  }

  public void setPost(Post post) {
    this.post = post;
  }
}
