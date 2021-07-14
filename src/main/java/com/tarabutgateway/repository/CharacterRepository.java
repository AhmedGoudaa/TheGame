package com.tarabutgateway.repository;

import com.tarabutgateway.entity.Character;

import java.util.Optional;
import java.util.Set;

public interface CharacterRepository extends Repository {

  Optional<Character> get(String id) ;

  Set<Character> getAll();

  Character save(Character newCharacter);

  Character update(Character updatedCharacter);

  boolean delete(Character updatedCharacter);
}
