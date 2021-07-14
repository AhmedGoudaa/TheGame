package com.xgame.comman;

import java.util.ServiceLoader;
import java.util.Set;

public interface MessageBus {
  /**
   * registers a new Subscriber to this MessageBus instance
   */
  void register(Subscriber<?> subscriber);


  void send(Message<?> message);

  /**
   * get the list of all the subscribers associated with this MessageBus instance
   */
  Set<Subscriber> getSubscribers();

  default void loadHandlers() {
    // load all the  implementations of Subscriber(Message handlers)
    // take a look at  src/main/resources/META-INF/services/
    ServiceLoader<Subscriber> subscriberServiceLoader = ServiceLoader.load(Subscriber.class);
    for (Subscriber subscriber : subscriberServiceLoader) {
      register(subscriber);
    }
  }
}
