package com.xgame.exceptions;

public class CharacterNotFound extends Exception {
  private final String errorMessage;

  public CharacterNotFound(String errorMessage) {
    this.errorMessage = errorMessage;
  }

  public CharacterNotFound() {
    this("Character Not found!");
  }

  public String getErrorMessage() {
    return errorMessage;
  }
}
