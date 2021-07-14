package com.xgame.handler;

import com.xgame.comman.MessageBus;
import com.xgame.comman.MessageBusFactory;
import com.xgame.comman.Subscriber;
import com.xgame.entity.Character;
import com.xgame.factory.RepositoryFactory;
import com.xgame.message.CreateNewCharacter;
import com.xgame.message.LogMessage;
import com.xgame.repository.CharacterRepository;

public class CreateNewCharacterHandler implements Subscriber<CreateNewCharacter> {

  private final CharacterRepository characterRepository;
  private final MessageBus          messageBus;

  public CreateNewCharacterHandler(CharacterRepository characterRepository, MessageBus messageBus) {
    this.characterRepository = characterRepository;
    this.messageBus          = messageBus;
  }

  public CreateNewCharacterHandler() {
    this(RepositoryFactory.getCharacterInstance(), MessageBusFactory.getDefaultInstance());
  }

  @Override
  public void handle(CreateNewCharacter characterMessage) {

    var data = characterMessage.getData();

    var optionalCharacter =
            characterRepository.getAll()
                    .stream()
                    .filter(character -> character.getCharacterName().equals(data.getCharacterName()))
                    .findFirst();

    if (optionalCharacter.isPresent()) {
      messageBus.send(LogMessage.of(LogMessage.Log.ERROR, "Character Already Exists"));

    } else {
      characterRepository.save(new Character(data.getCharacterName(), data.getPower()));

      messageBus.send(LogMessage.of("Character saved!"));
    }

  }

  @Override
  public Class<CreateNewCharacter> getMessageClass() {
    return CreateNewCharacter.class;
  }
}
