package com.tarabutgateway.comman;

public interface Subscriber<T extends Message> {

  void handle(T tMessage);

  Class<T> getMessageClass();
}
