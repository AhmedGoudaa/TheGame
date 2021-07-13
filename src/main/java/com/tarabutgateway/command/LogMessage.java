package com.tarabutgateway.command;

import com.tarabutgateway.comman.Message;

public class LogMessage implements Message<String> {

  private final String message;

  public LogMessage(String message) {
    this.message = message;
  }

  @Override
  public String getData() {
    return message;
  }


}
