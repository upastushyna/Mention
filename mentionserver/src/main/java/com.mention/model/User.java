package com.mention.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.sql.Date;
import java.util.List;

@Entity
@Data
public class User {
  @Id
  @Column(name = "user_id")
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  private Long id;

  @OneToMany(mappedBy = "author")
  @JsonIgnoreProperties("author")
  private List<Post> posts;

  @OneToMany(mappedBy = "sender")
  @JsonIgnoreProperties("sender")
  private List<Message> sentMessages;

  @OneToMany(mappedBy = "receiver")
  @JsonIgnoreProperties("receiver")
  private List<Message> receivedMessages;

  @OneToMany(mappedBy = "commentator")
  @JsonIgnoreProperties("commentator")
  private List<Comment> comments;

  @OneToMany(mappedBy = "user")
  @JsonIgnoreProperties("user")
  private List<Favorite> favorites;

  @OneToMany(mappedBy = "user1")
  @JsonIgnoreProperties(value = {"user1"})
  private List<Chat> chats;

  @OneToMany(mappedBy = "follower")
  @JsonIgnoreProperties(value = {"follower"})
  private List<Follow> followers;

  @OneToMany(mappedBy = "signer")
  @JsonIgnoreProperties(value = {"signer"})
  private List<Follow> signers;

  @OneToOne
  @JoinColumn(name = "profile_id", nullable = false, updatable = false, unique = true)
  @JsonIgnoreProperties(value = {"user"})
  private Profile profile;

  @Column(nullable = false, unique = true, name = "user_username")
  private String username;

  @Column(nullable = false, unique = true, name = "user_email")
  private String email;

  @Column(nullable = false, name = "user_password")
  private String password;

  @Column(nullable = false, name = "user_is_active")
  private boolean isActive;

  public void setActive(boolean active) {
    isActive = active;
  }
}