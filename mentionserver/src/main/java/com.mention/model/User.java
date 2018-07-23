package com.mention.model;

import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.sql.Date;
import java.util.List;

@Entity
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  private Long user_id;

  @OneToMany(mappedBy = "post_author")
  private List<Post> user_posts;

  @OneToMany(mappedBy = "")
  private List<Comment> comments;

  @Column(nullable = false, unique = true)
  private String user_username;

  @Column
  private String user_firstName;

  @Column
  private String user_secondName;

  @Column
  private String user_address;

  @Column
  private Date user_birthDate;

  @Column
  private String user_avatarUrl;

  @Column
  private String user_backgroundUrl;

  @Column(nullable = false, unique = true)
  private String user_email;

  @Column(nullable = false)
  private String user_password;

  @Column(nullable = false)
  private boolean user_isActive;

  public String getUsername() {
    return user_username;
  }

  public void setUsername(String username) {
    this.user_username = username;
  }

  public Long getId() {
    return user_id;
  }

  public void setId(Long id) {
    this.user_id = id;
  }

  public String getFirstName() {
    return user_firstName;
  }

  public void setFirstName(String firstName) {
    this.user_firstName = firstName;
  }

  public String getSecondName() {
    return user_secondName;
  }

  public void setSecondName(String secondName) {
    this.user_secondName = secondName;
  }

  public String getAddress() {
    return user_address;
  }

  public void setAddress(String address) {
    this.user_address = address;
  }

  public Date getBirthDate() {
    return user_birthDate;
  }

  public void setBirthDate(Date birthDate) {
    this.user_birthDate = birthDate;
  }

  public String getAvatarUrl() {
    return user_avatarUrl;
  }

  public void setAvatarUrl(String avatarUrl) {
    this.user_avatarUrl = avatarUrl;
  }

  public String getBackgroundUrl() {
    return user_backgroundUrl;
  }

  public void setBackgroundUrl(String backgroundUrl) {
    this.user_backgroundUrl = backgroundUrl;
  }

  public String getEmail() {
    return user_email;
  }

  public void setEmail(String email) {
    this.user_email = email;
  }

  public String getPassword() {
    return user_password;
  }

  public void setPassword(String password) {
    this.user_password = password;
  }

  public boolean isActive() {
    return user_isActive;
  }

  public void setActive(boolean active) {
    user_isActive = active;
  }
}
