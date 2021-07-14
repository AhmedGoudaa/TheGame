package com.xgame.message;

import com.xgame.comman.Message;
import com.xgame.entity.Character;

public class ChoseCharacter implements Message<Character> {

  private final Character character;

  public ChoseCharacter(Character character) {
    this.character = character;
  }

  @Override
  public Character getData() {
    return this.character;
  }

}
