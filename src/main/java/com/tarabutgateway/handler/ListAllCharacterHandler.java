package com.tarabutgateway.handler;

import com.tarabutgateway.comman.MessageBus;
import com.tarabutgateway.comman.MessageBusFactory;
import com.tarabutgateway.comman.Subscriber;
import com.tarabutgateway.command.ListAllCharacters;
import com.tarabutgateway.command.LogMessage;
import com.tarabutgateway.factory.repo.CharacterRepoFactory;
import com.tarabutgateway.repository.CharacterRepository;


public class ListAllCharacterHandler implements Subscriber<ListAllCharacters> {

  private final CharacterRepository characterRepository;
  private final MessageBus          messageBus;


  public ListAllCharacterHandler(CharacterRepository characterRepository, MessageBus messageBus) {
    this.characterRepository = characterRepository;
    this.messageBus          = messageBus;
  }

  public ListAllCharacterHandler() {
    this(CharacterRepoFactory.getInstance(), MessageBusFactory.getDefaultInstance());
  }

  @Override
  public void handle(ListAllCharacters listAllCharacters) {
    characterRepository.getAll()
            .forEach(character -> messageBus.send(new LogMessage(character.toString())));
  }

  @Override
  public Class<ListAllCharacters> getMessageClass() {
    return ListAllCharacters.class;
  }
}
