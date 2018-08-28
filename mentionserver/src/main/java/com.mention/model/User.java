package com.mention.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.Arrays;
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

  @OneToMany(mappedBy = "commentator")
  @JsonIgnoreProperties("commentator")
  private List<Comment> comments;

  @OneToMany(mappedBy = "user1")
  @JsonIgnoreProperties(value = {"user1"})
  private List<Chat> chats;

  @OneToOne(mappedBy = "user")
  @JsonManagedReference
  private Profile profile;

  @OneToMany(mappedBy = "follower")
  @JsonIgnoreProperties(value = {"follower"})
  private List<Follow> followedUsers;

  @OneToMany(mappedBy = "followedUser")
  @JsonIgnoreProperties(value = {"followedUser"})
  private List<Follow> followers;

  @Column(nullable = false, unique = true, name = "user_username")
  private String username;

  @Column(nullable = false, unique = true, name = "user_email")
  private String email;

  @Column(nullable = false, name = "user_password")
  private String password;

  @Column(nullable = false, name = "user_is_active")
  private boolean isActive;

  @OneToMany(mappedBy = "user")
  @JsonIgnoreProperties(value = {"user"})
  private List<PostLike> postLikes;

  @OneToMany(mappedBy = "user")
  @JsonIgnoreProperties(value = {"user"})
  private List<CommentLike> commentLikes;

  protected User() {
  }

  public User(Long id) {
    this.id = id;
  }

  public User(String username, String email, String password, boolean isActive) {
    this.username = username;
    this.email = email;
    this.password = password;
    this.isActive = isActive;
  }

  public List<String> getRoles() {
    return Arrays.asList("ROLE_USER");
  }

  public String toString1() {
    return String.format("_User{id=%d, username='%s', email='%s', password='%s'}", id, username, email, password);
  }
}