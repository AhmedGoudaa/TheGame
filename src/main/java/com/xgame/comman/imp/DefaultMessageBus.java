package com.xgame.comman.imp;

import com.xgame.comman.Message;
import com.xgame.comman.MessageBus;
import com.xgame.comman.Subscriber;
import com.xgame.comman.SubscriberNotExist;

import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@SuppressWarnings("unchecked")
public class DefaultMessageBus implements MessageBus {

  protected final Map<Class<?>, Set<Subscriber>> subscriberMap;

  public DefaultMessageBus() {
    this.subscriberMap = new ConcurrentHashMap<>();
  }

  @Override
  public void register(Subscriber<?> subscriber) {
    var messageClass = subscriber.getMessageClass();

    subscriberMap.merge(messageClass, Set.of(subscriber),
            (oldValue, newVal) ->
                    Stream.concat(oldValue.stream(), newVal.stream())
                            .collect(Collectors.toSet()));
  }

  @Override
  public void send(Message<?> message) {
    var messageClass = message.getClass();
    var subscribers  = subscriberMap.get(messageClass);

    if (subscribers != null) {
      subscribers.forEach(subscriber -> subscriber.handle(message));
    } else {
      System.out.printf("Can't handling message of typ %s", messageClass);
      throw new SubscriberNotExist(messageClass);
    }
  }

  @Override
  public Set<Subscriber> getSubscribers() {
    Set<Subscriber> collect = subscriberMap.values().stream().flatMap(Collection::stream).collect(Collectors.toSet());

    return collect;
  }
}
