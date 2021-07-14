package com.tarabutgateway.command;

import com.tarabutgateway.comman.Message;

public class ShutdownCommand implements Message<Void> {

  @Override
  public Void getData() {
    return null;
  }
}
