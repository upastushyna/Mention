package com.mention.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
@Data
public class UserToken {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  @Column(name = "token_id")
  private Long id;

  @Column(name = "token")
  private String token;

  @OneToOne
  @JoinColumn(name = "user_id", updatable = false, unique = true, nullable = false)
  private User user;
}
