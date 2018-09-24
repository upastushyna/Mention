package com.mention.dto;

import lombok.Data;

import javax.validation.constraints.Email;

@Data
public class ForgotPasswordRq {

  @Email
  private String email;
}