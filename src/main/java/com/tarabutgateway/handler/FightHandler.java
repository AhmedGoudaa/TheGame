package com.tarabutgateway.handler;

import com.tarabutgateway.comman.MessageBus;
import com.tarabutgateway.comman.MessageBusFactory;
import com.tarabutgateway.comman.Subscriber;
import com.tarabutgateway.command.FightAction;
import com.tarabutgateway.command.LogMessage;
import com.tarabutgateway.command.UpdateCurrentPlayerPower;
import com.tarabutgateway.entity.Character;
import com.tarabutgateway.entity.FightData;
import com.tarabutgateway.entity.Player;
import com.tarabutgateway.factory.repo.CharacterRepoFactory;
import com.tarabutgateway.factory.repo.PlayerRepoFactory;
import com.tarabutgateway.repository.CharacterRepository;
import com.tarabutgateway.repository.PlayerRepository;

public class FightHandler implements Subscriber<FightAction> {

  private final PlayerRepository    playerRepository;
  private final MessageBus          messageBus;

  public FightHandler(
          PlayerRepository playerRepository,
          MessageBus messageBus) {

    this.playerRepository    = playerRepository;
    this.messageBus          = messageBus;
  }

  public FightHandler() {
    this(PlayerRepoFactory.getInstance(),
            MessageBusFactory.getDefaultInstance());
  }

  @Override
  public void handle(FightAction fightAction) {
    FightData data = fightAction.getData();

    Player currentPlayer = playerRepository.getCurrentPlayer();

    Character currentCharacter = currentPlayer.getCurrentCharacter();
    if (currentCharacter == null) {
      messageBus.send(new LogMessage("Chose a character first"));
    } else {

      int charVal = fightAction.getData().getKeyboardKey();

      if (charVal > data.getFightAgainst().getPower()) {
        messageBus.send(new LogMessage("U won!!!"));
        messageBus.send(new UpdateCurrentPlayerPower(data.getFightAgainst().getPower()));
      } else {
        messageBus.send(new LogMessage(LogMessage.Log.ERROR,"U Lost."));
        messageBus.send(new UpdateCurrentPlayerPower(-data.getFightAgainst().getPower()));
      }
    }

  }

  @Override
  public Class<FightAction> getMessageClass() {
    return FightAction.class;
  }
}
