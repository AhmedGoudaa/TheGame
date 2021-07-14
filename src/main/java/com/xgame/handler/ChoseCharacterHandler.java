package com.xgame.handler;

import com.xgame.comman.MessageBus;
import com.xgame.comman.MessageBusFactory;
import com.xgame.comman.Subscriber;
import com.xgame.entity.Player;
import com.xgame.factory.RepositoryFactory;
import com.xgame.message.ChoseCharacter;
import com.xgame.message.LogMessage;
import com.xgame.repository.PlayerRepository;


public class ChoseCharacterHandler implements Subscriber<ChoseCharacter> {

  private final PlayerRepository playerRepository;
  private final MessageBus       messageBus;

  public ChoseCharacterHandler(PlayerRepository playerRepository,
                               MessageBus messageBus) {
    this.playerRepository = playerRepository;
    this.messageBus       = messageBus;
  }

  public ChoseCharacterHandler() {
    this(RepositoryFactory.getPlayerInstance(), MessageBusFactory.getDefaultInstance());
  }

  @Override
  public void handle(ChoseCharacter choseCharacter) {

    Player currentPlayer = playerRepository.getCurrentPlayer();
    currentPlayer.setCurrentCharacter(choseCharacter.getData());
    playerRepository.updateCurrentPlayer(currentPlayer);
    messageBus.send(LogMessage.of("character has chosen."));
  }

  @Override
  public Class<ChoseCharacter> getMessageClass() {
    return ChoseCharacter.class;
  }
}
