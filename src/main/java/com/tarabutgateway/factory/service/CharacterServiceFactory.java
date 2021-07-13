package com.tarabutgateway.factory.service;

import com.tarabutgateway.factory.Factory;
import com.tarabutgateway.factory.repo.CharacterRepoFactory;
import com.tarabutgateway.service.CharacterService;
import com.tarabutgateway.service.impl.CharacterServiceImpl;

public class CharacterServiceFactory implements Factory<CharacterService> {

  private final CharacterRepoFactory characterRepoFactory;

  public CharacterServiceFactory() {
    this.characterRepoFactory = new CharacterRepoFactory();
  }

  @Override
  public CharacterService getInstance() {
    return new CharacterServiceImpl(characterRepoFactory.getInstance());
  }
}
