package com.tarabutgateway.comman;

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
}
