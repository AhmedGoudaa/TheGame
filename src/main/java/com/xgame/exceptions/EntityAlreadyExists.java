package com.xgame.exceptions;

public class EntityAlreadyExists extends Exception {
  private final String errorMessage;

  public EntityAlreadyExists(String errorMessage) {
    this.errorMessage = errorMessage;
  }

  public EntityAlreadyExists() {
    this("Already Exists!");
  }

  public String getErrorMessage() {
    return errorMessage;
  }
}
