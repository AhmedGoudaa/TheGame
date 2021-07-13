package com.tarabutgateway.handler;

import com.tarabutgateway.comman.MessageBus;
import com.tarabutgateway.comman.MessageBusFactory;
import com.tarabutgateway.comman.Subscriber;
import com.tarabutgateway.command.CreateNewCharacter;
import com.tarabutgateway.command.LogMessage;
import com.tarabutgateway.entity.Character;
import com.tarabutgateway.factory.repo.CharacterRepoFactory;
import com.tarabutgateway.repository.CharacterRepository;

public class CreateNewCharacterHandler implements Subscriber<CreateNewCharacter> {

  private final CharacterRepository characterRepository;
  private final MessageBus          messageBus;

  public CreateNewCharacterHandler() {
    this.characterRepository = CharacterRepoFactory.getInstance();
    this.messageBus          = MessageBusFactory.getDefaultInstance();
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
      messageBus.send(new LogMessage("Character Already Exists"));

    } else {
      characterRepository.save(new Character(data.getCharacterName(), data.getPower()));

      messageBus.send(new LogMessage("Character saved!"));
    }

  }

  @Override
  public Class<CreateNewCharacter> getMessageClass() {
    return CreateNewCharacter.class;
  }
}
