package com.xgame.message;

import com.xgame.comman.Message;

import java.util.Objects;

public class UpdateCurrentPlayerPower implements Message<Integer> {

  private final int powerDelta;

  public UpdateCurrentPlayerPower(int power) {
    this.powerDelta = power;
  }

  @Override
  public Integer getData() {
    return powerDelta;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    UpdateCurrentPlayerPower that = (UpdateCurrentPlayerPower) o;
    return powerDelta == that.powerDelta;
  }

  @Override
  public int hashCode() {
    return Objects.hash(powerDelta);
  }
}
