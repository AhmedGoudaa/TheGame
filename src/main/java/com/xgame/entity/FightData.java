package com.xgame.entity;

import java.util.Objects;

public class FightData {
  private final char      keyboardKey;
  private final Character fightAgainst;

  public FightData(char keyboardKey, Character fightAgainst) {
    this.keyboardKey  = keyboardKey;
    this.fightAgainst = fightAgainst;
  }

  public char getKeyboardKey() {
    return keyboardKey;
  }

  public Character getFightAgainst() {
    return fightAgainst;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    FightData fightData = (FightData) o;
    return Objects.equals(keyboardKey, fightData.keyboardKey) && Objects.equals(fightAgainst, fightData.fightAgainst);
  }

  @Override
  public int hashCode() {
    return Objects.hash(keyboardKey, fightAgainst);
  }
}
