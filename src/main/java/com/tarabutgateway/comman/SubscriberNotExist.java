package com.tarabutgateway.comman;

public class SubscriberNotExist extends RuntimeException {
  public SubscriberNotExist(Class<?> messageClass) {
    super(messageClass.getName());
  }
}
