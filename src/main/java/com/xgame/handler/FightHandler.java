package com.xgame.handler;

import com.xgame.comman.MessageBus;
import com.xgame.comman.MessageBusFactory;
import com.xgame.comman.Subscriber;
import com.xgame.entity.Character;
import com.xgame.entity.FightData;
import com.xgame.entity.Player;
import com.xgame.factory.RepositoryFactory;
import com.xgame.message.FightAction;
import com.xgame.message.LogMessage;
import com.xgame.message.UpdateCurrentPlayerPower;
import com.xgame.repository.PlayerRepository;

public class FightHandler implements Subscriber<FightAction> {

  private final PlayerRepository playerRepository;
  private final MessageBus       messageBus;

  public FightHandler(
          PlayerRepository playerRepository,
          MessageBus messageBus) {

    this.playerRepository = playerRepository;
    this.messageBus       = messageBus;
  }

  public FightHandler() {
    this(RepositoryFactory.getPlayerInstance(),
            MessageBusFactory.getDefaultInstance());
  }

  @Override
  public void handle(FightAction fightAction) {
    FightData data = fightAction.getData();

    Player currentPlayer = playerRepository.getCurrentPlayer();

    Character currentCharacter = currentPlayer.getCurrentCharacter();
    if (currentCharacter == null) {
      messageBus.send(LogMessage.of("Chose a character first"));
    } else {

      int charVal = fightAction.getData().getKeyboardKey();

      if (charVal > data.getFightAgainst().getPower()) {
        messageBus.send(LogMessage.of("U won!!!"));
        messageBus.send(new UpdateCurrentPlayerPower(data.getFightAgainst().getPower()));
      } else {
        messageBus.send(LogMessage.of(LogMessage.Log.ERROR, "U Lost."));
        messageBus.send(new UpdateCurrentPlayerPower(-data.getFightAgainst().getPower()));
      }
    }

  }

  @Override
  public Class<FightAction> getMessageClass() {
    return FightAction.class;
  }
}
