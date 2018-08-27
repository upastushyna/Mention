package com.mention.exceptions;

public class UserNotConfirmedException extends Exception {

  public UserNotConfirmedException(String message) {
    super(message);
  }

  public UserNotConfirmedException(String message, Throwable cause) {
    super(message, cause);
  }
}
