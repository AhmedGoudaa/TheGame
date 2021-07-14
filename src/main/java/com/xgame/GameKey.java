package com.xgame;

public enum GameKey {

  EXIT('e', "To close the game press key %s"),
  PRINT_STATE('p', "To showing your state (Score) press key %s"),
  CHOOSE_CHAR('c', "To choose character press key %s"),
  FIGHT('f', "To start fighting press key %s"),
  NEW_CHAR('n', "To creating new character press key %s");


  /**
   * the representative keyboard character to start the command.
   */
  private final char   key;
  /**
   * @return description of what this command is doing.
   */
  private final String commandDescription;

  GameKey(char key, String commandDescription) {
    this.key                = key;
    this.commandDescription = commandDescription;
  }

  public char getKey() {
    return key;
  }

  public String getCommandDescription() {
    return commandDescription;
  }
}
