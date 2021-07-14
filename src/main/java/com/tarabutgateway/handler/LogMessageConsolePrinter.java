package com.tarabutgateway.handler;

import com.tarabutgateway.comman.Subscriber;
import com.tarabutgateway.command.LogMessage;

public class LogMessageConsolePrinter implements Subscriber<LogMessage> {

  @Override
  public void handle(LogMessage logMessageMessage) {

    System.out.println(logMessageMessage.getLogType().getColor() + logMessageMessage.getData());
  }

  @Override
  public Class<LogMessage> getMessageClass() {
    return LogMessage.class;
  }
}
