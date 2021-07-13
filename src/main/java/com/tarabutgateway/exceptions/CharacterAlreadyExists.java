package com.tarabutgateway.exceptions;

public class CharacterAlreadyExists extends Exception {
  private final String errorMessage;

  public CharacterAlreadyExists(String errorMessage) {
    this.errorMessage = errorMessage;
  }

  public CharacterAlreadyExists() {
    this("Character Already Exists!");
  }

  public String getErrorMessage() {
    return errorMessage;
  }
}
