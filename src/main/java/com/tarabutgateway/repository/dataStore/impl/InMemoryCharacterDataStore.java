package com.tarabutgateway.repository.dataStore.impl;


import com.tarabutgateway.entity.Character;
import com.tarabutgateway.repository.dataStore.DataStore;

import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class InMemoryCharacterDataStore implements DataStore<Character> {

  private final Map<String, Character> characterMap = new ConcurrentHashMap<>();

  @Override
  public void save(Character character) {
    characterMap.merge(character.getId(), character,
            (oldVal, newVal) -> {

              if (!Objects.equals(oldVal, newVal)) {
                return newVal;
              }
              return oldVal;
            });
  }

  @Override
  public void delete(Character character) {
    characterMap.remove(character.getId());
  }

  @Override
  public void load() {
  }

  @Override
  public Character get() {
    return null;
  }

  @Override
  public Set<Character> getAll() {
    return Set.copyOf(characterMap.values());
  }
}
