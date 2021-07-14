package com.xgame.handler;

import com.xgame.comman.Subscriber;
import com.xgame.factory.DataStoreFactory;
import com.xgame.message.Shutdown;

import java.util.stream.Stream;


public class OnShutdownHandler implements Subscriber<Shutdown> {

  @Override
  public void handle(Shutdown Shutdown) {
    Stream.of(DataStoreFactory.PresistableDataStores.values())
            .forEach(presistableDataStore -> presistableDataStore.getInstance().saveToFile());
  }

  @Override
  public Class<Shutdown> getMessageClass() {
    return Shutdown.class;
  }
}
