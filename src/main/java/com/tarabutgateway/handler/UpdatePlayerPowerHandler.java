package com.tarabutgateway.handler;

import com.tarabutgateway.comman.MessageBus;
import com.tarabutgateway.comman.MessageBusFactory;
import com.tarabutgateway.comman.Subscriber;
import com.tarabutgateway.command.LogMessage;
import com.tarabutgateway.command.UpdateCurrentPlayerPower;
import com.tarabutgateway.entity.Player;
import com.tarabutgateway.factory.repo.PlayerRepoFactory;
import com.tarabutgateway.repository.PlayerRepository;


public class UpdatePlayerPowerHandler implements Subscriber<UpdateCurrentPlayerPower> {

  private final PlayerRepository playerRepository;
  private final MessageBus       messageBus;

  public UpdatePlayerPowerHandler(PlayerRepository playerRepository,
                                  MessageBus messageBus) {
    this.playerRepository = playerRepository;
    this.messageBus       = messageBus;
  }

  public UpdatePlayerPowerHandler() {
    this(PlayerRepoFactory.getInstance(), MessageBusFactory.getDefaultInstance());
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

    messageBus.send(new LogMessage("Player Score updated"));
  }

  @Override
  public Class<UpdateCurrentPlayerPower> getMessageClass() {
    return UpdateCurrentPlayerPower.class;
  }
}
