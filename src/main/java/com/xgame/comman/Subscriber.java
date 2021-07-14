package com.xgame.comman;

public interface Subscriber<T extends Message> {

  void handle(T tMessage);

  Class<T> getMessageClass();
}
