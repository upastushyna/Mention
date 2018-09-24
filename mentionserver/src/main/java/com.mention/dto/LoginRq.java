package com.mention.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class LoginRq {

  @NotBlank
  private String usernameOrEmail;

  @NotBlank
  private String password;

  @JsonProperty
  private boolean isRemembered;
}