package com.tarabutgateway.service.impl;

import com.tarabutgateway.entity.Character;
import com.tarabutgateway.exceptions.CharacterAlreadyExists;
import com.tarabutgateway.exceptions.CharacterNotFound;
import com.tarabutgateway.repository.CharacterRepository;
import com.tarabutgateway.service.CharacterService;

public class CharacterServiceImpl implements CharacterService {
  private final CharacterRepository characterRepository;

  public CharacterServiceImpl(CharacterRepository characterRepository) {
    this.characterRepository = characterRepository;
  }

  @Override
  public Character findCharacter(String characterName) throws CharacterNotFound {
    return characterRepository.get(characterName)
            .orElseThrow(() -> new CharacterNotFound(characterName + " Character Not found!"));
  }

  @Override
  public Character createNewCharacter(String characterName, int power) throws CharacterAlreadyExists {

    var optionalCharacter =
            characterRepository.getAll()
                    .stream()
                    .filter(character -> character.getCharacterName().equals(characterName))
                    .findFirst();

    if (optionalCharacter.isPresent()) {
      throw new CharacterAlreadyExists();
    }

    return characterRepository.save(new Character(characterName, power));
  }

  @Override
  public Character updateCharacter(String characterName, int power) throws CharacterNotFound {

    Character character = characterRepository.get(characterName)
            .orElseThrow(() -> new CharacterNotFound(characterName + " Character Not found!"));

    character.setPower(power);
    return characterRepository.update(character);
  }
}
