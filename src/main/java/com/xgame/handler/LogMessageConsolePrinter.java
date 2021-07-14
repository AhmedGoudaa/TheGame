package com.xgame.handler;

import com.xgame.comman.Subscriber;
import com.xgame.message.LogMessage;

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
