package com.xgame.comman;

public class SubscriberNotExist extends RuntimeException {
  public SubscriberNotExist(Class<?> messageClass) {
    super(messageClass.getName());
  }
}
