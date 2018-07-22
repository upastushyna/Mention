package com.mention.model;

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
  private Long id;

  @OneToMany(mappedBy = "author")
  private List<Post> posts;

  @OneToMany(mappedBy = "sender")
  private List<Message> sentMessages;

  @OneToMany(mappedBy = "receiver")
  private List<Message> receivedMessages;

  @Column(nullable = false, unique = true, name = "USER_USERNAME")
  private String username;

  @Column(name = "USER_FIRSTNAME")
  private String firstName;

  @Column(name = "USER_SECONDNAME")
  private String secondName;

  @Column(name = "USER_ADDRESS")
  private String address;

  @Column(name = "USER_BIRTHDATE")
  private Date birthDate;

  @Column(name = "USER_AVATAR_URL")
  private String avatarUrl;

  @Column(name = "USER_BACKGROUND_URL")
  private String backgroundUrl;

  @Column(nullable = false, unique = true, name = "USER_EMAIL")
  private String email;

  @Column(nullable = false, name = "USER_PASSWORD")
  private String password;

  @Column(nullable = false, name = "USER_IS_ACTIVE")
  private boolean isActive;

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getSecondName() {
    return secondName;
  }

  public void setSecondName(String secondName) {
    this.secondName = secondName;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public Date getBirthDate() {
    return birthDate;
  }

  public void setBirthDate(Date birthDate) {
    this.birthDate = birthDate;
  }

  public String getAvatarUrl() {
    return avatarUrl;
  }

  public void setAvatarUrl(String avatarUrl) {
    this.avatarUrl = avatarUrl;
  }

  public String getBackgroundUrl() {
    return backgroundUrl;
  }

  public void setBackgroundUrl(String backgroundUrl) {
    this.backgroundUrl = backgroundUrl;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public boolean isActive() {
    return isActive;
  }

  public void setActive(boolean active) {
    isActive = active;
  }

  public List<Message> getReceivedMessages() {
    return receivedMessages;
  }

  public void setReceivedMessages(List<Message> receivedMessages) {
    this.receivedMessages = receivedMessages;
  }

  public List<Message> getSentMessages() {
    return sentMessages;
  }

  public void setSentMessages(List<Message> sentMessages) {
    this.sentMessages = sentMessages;
  }

  public List<Post> getPosts() {
    return posts;
  }

  public void setPosts(List<Post> posts) {
    this.posts = posts;
  }

}
