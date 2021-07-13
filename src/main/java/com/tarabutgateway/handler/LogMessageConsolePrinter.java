package com.tarabutgateway.handler;

import com.tarabutgateway.comman.Subscriber;
import com.tarabutgateway.command.LogMessage;

public class LogMessageConsolePrinter implements Subscriber<LogMessage> {

  @Override
  public void handle(LogMessage logMessageMessage) {
    System.out.println(logMessageMessage);
  }

  @Override
  public Class<LogMessage> getMessageClass() {
    return LogMessage.class;
  }
}
