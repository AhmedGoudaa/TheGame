package com.xgame.message;

import com.xgame.comman.Message;

public class LogMessage implements Message<String> {

  private final String message;
  private final Log    logType;


  private LogMessage(Log logType, String message) {
    this.logType = logType;
    this.message = message;
  }

  private LogMessage(String message) {
    this(Log.INFO, message);
  }

  public static LogMessage of(Log logType, String message) {
    return new LogMessage(logType, message);
  }

  public static LogMessage of(String message) {
    return new LogMessage(message);
  }

  public static LogMessage read() {
    return new LogMessage("> ");
  }

  @Override
  public String getData() {
    return message;
  }

  public Log getLogType() {
    return logType;
  }

  public enum Log {

    INFO("\u001B[32m"),
    ERROR("\033[0;31m");

    private String color;

    Log(String color) {
      this.color = color;
    }

    public String getColor() {
      return color;
    }
  }


}
