package com.xgame.message;

import com.xgame.comman.Message;
import com.xgame.entity.Character;

public class CreateNewCharacter implements Message<Character> {

  private final Character character;

  public CreateNewCharacter(Character character) {
    this.character = character;
  }

  @Override
  public Character getData() {
    return character;
  }

//  @Override
//  public Class<Character> getMessageClass() {
//    return Character.class;
//  }
}
