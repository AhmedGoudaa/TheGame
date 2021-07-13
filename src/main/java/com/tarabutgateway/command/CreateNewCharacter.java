package com.tarabutgateway.command;

import com.tarabutgateway.comman.Message;
import com.tarabutgateway.entity.Character;

public class CreateNewCharacter implements Message<Character> {

  private final String name;
  private final int power;

  public CreateNewCharacter(String name, int power) {
    this.name  = name;
    this.power = power;
  }

  @Override
  public Character getData() {
    return new Character(name,power) ;
  }

//  @Override
//  public Class<Character> getMessageClass() {
//    return Character.class;
//  }
}
