package com.xgame.factory;

import com.xgame.service.CharacterService;
import com.xgame.service.impl.CharacterServiceImpl;

public class ServiceFactory {

  public static CharacterService getCharacterInstance() {
    return new CharacterServiceImpl(RepositoryFactory.getCharacterInstance());
  }


}
