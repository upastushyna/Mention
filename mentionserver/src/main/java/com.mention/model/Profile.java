package com.mention.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import java.sql.Date;

@Entity
@Data
public class Profile {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  @Column(name = "profile_id")
  private Long id;

  @Column(name = "profile_firstname")
  private String firstName;

  @Column(name = "profile_secondname")
  private String secondName;

  @Column(name = "profile_address")
  private String address;

  @Column(name = "profile_birthdate")
  private Date birthDate;

  @Column(name = "profile_avatar_url")
  private String avatarUrl;

  @Column(name = "profile_background_url")
  private String backgroundUrl;

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "user_id", updatable = false, unique = true)
  @JsonIgnoreProperties(value = {"profile", "sentMessages", "posts", "comments", "receivedMessages", "chats", "favorites"})
  private User user;

}
