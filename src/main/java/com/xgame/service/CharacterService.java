package com.xgame.service;

import com.xgame.entity.Character;

import java.util.Optional;

public interface CharacterService {

  Optional<Character> findCharacter(String characterName);
}
