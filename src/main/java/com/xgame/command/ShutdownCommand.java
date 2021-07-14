package com.xgame.command;

import com.xgame.GameKey;
import com.xgame.comman.MessageBus;
import com.xgame.comman.MessageBusFactory;
import com.xgame.message.LogMessage;
import com.xgame.message.Shutdown;

public class ShutdownCommand implements Command {

  private final MessageBus messageBus;


  public ShutdownCommand(MessageBus messageBus) {
    this.messageBus = messageBus;
  }

  public ShutdownCommand() {
    this(MessageBusFactory.getDefaultInstance());
  }

  @Override
  public GameKey commandKey() {
    return GameKey.EXIT;
  }

  @Override
  public void execute() {

    messageBus.send(LogMessage.of(LogMessage.Log.ERROR, "Shutting down..."));
    messageBus.send(new Shutdown());
    messageBus.send(LogMessage.of("See you later."));

  }


}
