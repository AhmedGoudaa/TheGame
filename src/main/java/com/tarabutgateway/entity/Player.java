package com.tarabutgateway.entity;

import java.util.Objects;

public class Player implements BaseEntity<String> {

  private final String    name;
  private       int       currentScore;
  private       Character currentCharacter;
  private       Level     currentLevel;

  public Player(String name) {
    this.name = name;
  }

  public Player(String name, int currentScore) {
    this.name         = name;
    this.currentScore = currentScore;
  }

  public Player(String name, int currentScore, Character currentCharacter) {
    this.name             = name;
    this.currentScore     = currentScore;
    this.currentCharacter = currentCharacter;
  }

  @Override
  public String getId() {
    return name;
  }

  public String getName() {
    return name;
  }

  public int getCurrentScore() {
    return currentScore;
  }

  public void setCurrentScore(int currentScore) {
    this.currentScore = currentScore;
  }


  public Character getCurrentCharacter() {
    return currentCharacter;
  }

  public void setCurrentCharacter(Character currentCharacter) {
    this.currentCharacter = currentCharacter;
  }


  public Level getCurrentLevel() {
    return currentLevel;
  }

  public void setCurrentLevel(Level currentLevel) {
    this.currentLevel = currentLevel;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Player player = (Player) o;
    return Objects.equals(getId(), player.getId());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getId());
  }
}
