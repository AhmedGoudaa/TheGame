package com.tarabutgateway.entity;


import java.util.Objects;

public class Character implements BaseEntity<String> {

  private final String characterName;
  private       int    power;

  public Character(String characterName) {
    this.characterName = characterName;
    this.power         = 0;
  }

  public Character(String characterName, int power) {
    this(characterName);
    this.power = power;
  }


  @Override
  public String getId() {
    return characterName;
  }

  public String getCharacterName() {
    return characterName;
  }

  public int getPower() {
    return power;
  }

  public void setPower(int power) {
    this.power = power;
  }

  public void increasePower() {
    this.power++;
  }

  public void decreasePower() {
    this.power--;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Character character = (Character) o;
    return power == character.power && Objects.equals(characterName, character.characterName);
  }

  @Override
  public int hashCode() {
    return Objects.hash(characterName, power);
  }
}
