package com.xgame.handler;

import com.xgame.comman.MessageBus;
import com.xgame.comman.MessageBusFactory;
import com.xgame.comman.Subscriber;
import com.xgame.factory.RepositoryFactory;
import com.xgame.message.ListAllCharacters;
import com.xgame.message.LogMessage;
import com.xgame.repository.CharacterRepository;


public class ListAllCharacterHandler implements Subscriber<ListAllCharacters> {

  private final CharacterRepository characterRepository;
  private final MessageBus          messageBus;


  public ListAllCharacterHandler(CharacterRepository characterRepository, MessageBus messageBus) {
    this.characterRepository = characterRepository;
    this.messageBus          = messageBus;
  }

  public ListAllCharacterHandler() {
    this(RepositoryFactory.getCharacterInstance(), MessageBusFactory.getDefaultInstance());
  }

  @Override
  public void handle(ListAllCharacters listAllCharacters) {
    characterRepository.getAll()
            .forEach(character -> messageBus.send(LogMessage.of(character.toString())));
  }

  @Override
  public Class<ListAllCharacters> getMessageClass() {
    return ListAllCharacters.class;
  }
}
