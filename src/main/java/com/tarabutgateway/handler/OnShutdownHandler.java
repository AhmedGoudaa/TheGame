package com.tarabutgateway.handler;

import com.tarabutgateway.comman.MessageBus;
import com.tarabutgateway.comman.MessageBusFactory;
import com.tarabutgateway.comman.Subscriber;
import com.tarabutgateway.command.ShutdownCommand;
import com.tarabutgateway.command.LogMessage;
import com.tarabutgateway.command.ShutdownCommand;
import com.tarabutgateway.factory.repo.CharacterRepoFactory;
import com.tarabutgateway.factory.repo.DataStoreFactory;
import com.tarabutgateway.repository.CharacterRepository;

import java.util.stream.Stream;


public class OnShutdownHandler implements Subscriber<ShutdownCommand> {

  @Override
  public void handle(ShutdownCommand ShutdownCommand) {
    Stream.of(DataStoreFactory.PresistableDataStores.values())
            .forEach(presistableDataStore -> presistableDataStore.getInstance().saveToFile());
  }

  @Override
  public Class<ShutdownCommand> getMessageClass() {
    return ShutdownCommand.class;
  }
}
