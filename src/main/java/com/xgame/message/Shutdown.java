package com.xgame.message;

import com.xgame.comman.Message;

public class Shutdown implements Message<Void> {

  @Override
  public Void getData() {
    return null;
  }
}
