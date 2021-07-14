package com.xgame.command;

import com.xgame.GameKey;
import com.xgame.comman.MessageBus;
import com.xgame.comman.MessageBusFactory;
import com.xgame.factory.RepositoryFactory;
import com.xgame.factory.ServiceFactory;
import com.xgame.message.LogMessage;
import com.xgame.repository.PlayerRepository;
import com.xgame.service.PlayerService;

public class ShowPlayerScoreCommand implements Command {

  private final MessageBus       messageBus;
  private final PlayerRepository playerRepository;

  public ShowPlayerScoreCommand(MessageBus messageBus, PlayerRepository playerRepository) {
    this.messageBus    = messageBus;
    this.playerRepository = playerRepository;
  }

  public ShowPlayerScoreCommand() {
    this(MessageBusFactory.getDefaultInstance(), RepositoryFactory.getPlayerInstance());
  }

  @Override
  public GameKey commandKey() {
    return GameKey.PRINT_STATE;
  }

  @Override
  public void execute() {

    messageBus.send(LogMessage.of("Showing current player state"));
    messageBus.send(LogMessage.of("---------------------"));
    messageBus.send(LogMessage.of(playerRepository.getCurrentPlayer().toString()));
    messageBus.send(LogMessage.of("---------------------"));

  }


}
