package com.xgame.comman.imp;

import com.xgame.comman.Message;

import java.util.concurrent.CompletableFuture;

public class AsyncMessageBus extends DefaultMessageBus {

  public AsyncMessageBus() {
    super();
  }

  @Override
  public void send(Message<?> message) {

    var subscribers = subscriberMap.get(message.getClass());

    if (subscribers != null) {
      subscribers.forEach(subscriber -> CompletableFuture.runAsync(() -> subscriber.handle(message)));

    } else {

      System.out.printf("Can't handling message of typ %s", message.getClass());
    }
  }

}
