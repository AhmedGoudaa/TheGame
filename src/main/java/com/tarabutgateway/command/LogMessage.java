package com.tarabutgateway.command;

import com.tarabutgateway.comman.Message;

public class LogMessage implements Message<String> {

  private final String message;
  private final Log logType;

  public LogMessage(Log  logType, String message) {
    this.logType = logType;
    this.message = message;
  }
  public LogMessage(String message) {
    this(Log.INFO, message);
  }

  @Override
  public String getData() {
    return message;
  }

  public Log getLogType() {
    return logType;
  }

  public enum Log{

    INFO("\u001B[32m"),
    ERROR("\033[0;31m");

    Log(String color) {
      this.color = color;
    }

    private String color;

    public String getColor() {
      return color;
    }
  }

  public static void main(String[] args) {
    System.out.println(Log.INFO.color + " TESTT");
  }

}
