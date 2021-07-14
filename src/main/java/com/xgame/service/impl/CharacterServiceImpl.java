package com.xgame.service.impl;

import com.xgame.entity.Character;
import com.xgame.repository.CharacterRepository;
import com.xgame.service.CharacterService;

import java.util.Optional;

public class CharacterServiceImpl implements CharacterService {
  private final CharacterRepository characterRepository;

  public CharacterServiceImpl(CharacterRepository characterRepository) {
    this.characterRepository = characterRepository;
  }

  @Override
  public Optional<Character> findCharacter(String characterName) {
    return characterRepository.get(characterName);
  }

}
