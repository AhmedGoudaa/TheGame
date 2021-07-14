package com.xgame.comman;

import com.xgame.comman.imp.AsyncMessageBus;
import com.xgame.comman.imp.DefaultMessageBus;

public class MessageBusFactory {


  public static MessageBus getNewDefaultInstance() {
    return new DefaultMessageBus();
  }

  public static MessageBus getDefaultInstance() {
    return MessageBusInstances.DEFAULT.instance;
  }

  public static MessageBus getAsyncInstance() {
    return MessageBusInstances.ASYNC.instance;
  }

  /**
   * Thread safety singleton
   */
  enum MessageBusInstances {

    DEFAULT(new DefaultMessageBus()),
    ASYNC(new AsyncMessageBus());

    MessageBus instance;

    MessageBusInstances(MessageBus messageBus) {
      this.instance = messageBus;
    }

  }
}
