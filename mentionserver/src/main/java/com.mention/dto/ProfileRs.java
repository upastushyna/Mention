package com.mention.dto;

import lombok.Data;

import java.sql.Date;

@Data
public class ProfileRs {

  private Long id;

  private String firstName;

  private String secondName;

  private String address;

  private Date birthDate;

  private String avatarUrl;

  private String avatarKey;

  private String backgroundUrl;

  private String backgroundKey;

  private UserIdRq user;
}

