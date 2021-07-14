package com.tarabutgateway.handler;

import com.tarabutgateway.comman.MessageBus;
import com.tarabutgateway.comman.MessageBusFactory;
import com.tarabutgateway.comman.Subscriber;
import com.tarabutgateway.command.ChoseCharacter;
import com.tarabutgateway.command.LogMessage;
import com.tarabutgateway.factory.repo.CharacterRepoFactory;
import com.tarabutgateway.factory.repo.PlayerRepoFactory;
import com.tarabutgateway.repository.CharacterRepository;
import com.tarabutgateway.repository.PlayerRepository;


public class ChoseCharacterHandler implements Subscriber<ChoseCharacter> {

  private final PlayerRepository    playerRepository;
  private final CharacterRepository characterRepository;
  private final MessageBus          messageBus;

  public ChoseCharacterHandler(PlayerRepository playerRepository,
                               CharacterRepository characterRepository,
                               MessageBus messageBus) {
    this.playerRepository    = playerRepository;
    this.characterRepository = characterRepository;
    this.messageBus          = messageBus;
  }

  public ChoseCharacterHandler() {
    this(PlayerRepoFactory.getInstance(), CharacterRepoFactory.getInstance(), MessageBusFactory.getDefaultInstance());
  }

  @Override
  public void handle(ChoseCharacter choseCharacter) {
    characterRepository.getAll()
            .forEach(character -> messageBus.send(new LogMessage(character.toString())));
  }

  @Override
  public Class<ChoseCharacter> getMessageClass() {
    return ChoseCharacter.class;
  }
}
