package com.mention.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
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

  @Column(nullable = false, unique = true, name = "user_username")
  private String username;

  @Column(name = "user_firstname")
  private String firstName;

  @Column(name = "user_secondname")
  private String secondName;

  @Column(name = "user_address")
  private String address;

  @Column(name = "user_birthdate")
  private Date birthDate;

  @Column(name = "user_avatar_url")
  private String avatarUrl;

  @Column(name = "user_background_url")
  private String backgroundUrl;

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
