package com.xgame.repository.impl;

import com.xgame.entity.Character;
import com.xgame.repository.CharacterRepository;
import com.xgame.repository.dataStore.DataStore;

import java.util.Optional;
import java.util.Set;

public class CharacterRepositoryImpl implements CharacterRepository {

  private final DataStore<Character> characterDataStore;

  public CharacterRepositoryImpl(DataStore<Character> characterDataStore) {
    this.characterDataStore = characterDataStore;
  }


  @Override
  public Set<Character> getAll() {
    return characterDataStore.getAll();
  }


  @Override
  public Optional<Character> get(String name) {
    return getAll()
            .stream()
            .filter(character -> character.getCharacterName().equals(name))
            .findFirst();

  }

  @Override
  public Character save(Character newCharacter) {
    characterDataStore.save(newCharacter);

    return newCharacter;
  }

  @Override
  public Character update(Character updatedCharacter) {
    characterDataStore.save(updatedCharacter);
    return updatedCharacter;
  }

  @Override
  public boolean delete(Character updatedCharacter) {
    return false;
  }
}
