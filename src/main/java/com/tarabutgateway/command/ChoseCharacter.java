package com.tarabutgateway.command;

import com.tarabutgateway.comman.Message;
import com.tarabutgateway.entity.Character;

public class ChoseCharacter implements Message<String> {

  private final String characterName;

  public ChoseCharacter(String characterName) {
    this.characterName  = characterName;
  }

  @Override
  public String getData() {
    return this.characterName;
  }

}
