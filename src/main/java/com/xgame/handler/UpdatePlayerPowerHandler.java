package com.xgame.handler;

import com.xgame.comman.MessageBus;
import com.xgame.comman.MessageBusFactory;
import com.xgame.comman.Subscriber;
import com.xgame.entity.Player;
import com.xgame.factory.RepositoryFactory;
import com.xgame.message.LogMessage;
import com.xgame.message.UpdateCurrentPlayerPower;
import com.xgame.repository.PlayerRepository;


public class UpdatePlayerPowerHandler implements Subscriber<UpdateCurrentPlayerPower> {

  private final PlayerRepository playerRepository;
  private final MessageBus       messageBus;

  public UpdatePlayerPowerHandler(PlayerRepository playerRepository,
                                  MessageBus messageBus) {
    this.playerRepository = playerRepository;
    this.messageBus       = messageBus;
  }

  public UpdatePlayerPowerHandler() {
    this(RepositoryFactory.getPlayerInstance(), MessageBusFactory.getDefaultInstance());
  }

  @Override
  public void handle(UpdateCurrentPlayerPower updateCurrentPlayerPower) {

    Player currentPlayer = playerRepository.getCurrentPlayer();

    int newScore = currentPlayer.getCurrentScore() + updateCurrentPlayerPower.getData();

    if (newScore < 0) {
      newScore = 0;
    }

    currentPlayer.setCurrentScore(newScore);
    playerRepository.updateCurrentPlayer(currentPlayer);

    messageBus.send(LogMessage.of("Player Score updated"));
  }

  @Override
  public Class<UpdateCurrentPlayerPower> getMessageClass() {
    return UpdateCurrentPlayerPower.class;
  }
}
