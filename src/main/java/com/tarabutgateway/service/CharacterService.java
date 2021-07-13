package com.tarabutgateway.service;

import com.tarabutgateway.entity.Character;
import com.tarabutgateway.exceptions.CharacterAlreadyExists;
import com.tarabutgateway.exceptions.CharacterNotFound;

public interface CharacterService {

  Character findCharacter(String characterName) throws CharacterNotFound;
  Character createNewCharacter(String characterName, int power) throws CharacterAlreadyExists;
  Character updateCharacter(String characterName, int power)throws CharacterNotFound;
}
