package com.xgame.common;

import com.xgame.comman.Message;
import com.xgame.comman.MessageBus;
import com.xgame.comman.MessageBusFactory;
import com.xgame.comman.Subscriber;
import com.xgame.comman.SubscriberNotExist;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

//@ExtendWith(MockitoExtension.class)
public class MessageBusTest {


  @Test
  void register_subscribers_test() {

    var messageBusMock = mock(MessageBus.class);

    messageBusMock.register(mock(Subscriber.class));
    messageBusMock.register(mock(Subscriber.class));

    verify(messageBusMock, times(2)).register(any(Subscriber.class));
  }

  @Test
  void handle_message_test() {


    var messageBus = MessageBusFactory.getDefaultInstance();

    TestMessageSubscriber subscriber  = spy(new TestMessageSubscriber());
    TestMessageSubscriber subscriber2 = spy(new TestMessageSubscriber());

    messageBus.register(subscriber);
    messageBus.register(subscriber2);

    messageBus.send(new TestMessage());
    messageBus.send(new TestMessage());

    verify(subscriber, times(2)).handle(any(TestMessage.class));
    verify(subscriber2, times(2)).handle(any(TestMessage.class));
    assertThat(messageBus.getSubscribers().size(), is(2));

  }

  @Test
  void handle_message_with_not_exist_subscriber_test() {

    var messageBus = MessageBusFactory.getNewDefaultInstance();
    assertThrows(SubscriberNotExist.class, () -> messageBus.send(new TestMessage()));
  }


  static class TestMessageSubscriber implements Subscriber<TestMessage> {
    @Override
    public void handle(TestMessage testMessageMessage) {
      // nothing
    }

    @Override
    public Class<TestMessage> getMessageClass() {
      return TestMessage.class;
    }
  }

  static class TestMessage implements Message<Integer> {
    @Override
    public Integer getData() {
      return Integer.MAX_VALUE;
    }
  }


}
