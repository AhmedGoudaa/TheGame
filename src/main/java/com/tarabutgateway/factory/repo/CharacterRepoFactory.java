package com.tarabutgateway.factory.repo;

import com.tarabutgateway.repository.CharacterRepository;
import com.tarabutgateway.repository.dataStore.impl.InMemoryCharacterDataStore;
import com.tarabutgateway.repository.impl.CharacterRepositoryImpl;

public class CharacterRepoFactory  {


  public static CharacterRepository getInstance() {
    return new CharacterRepositoryImpl(new InMemoryCharacterDataStore());
  }
}
