package com.xgame.factory;

import com.xgame.repository.CharacterRepository;
import com.xgame.repository.PlayerRepository;
import com.xgame.repository.impl.CharacterRepositoryImpl;
import com.xgame.repository.impl.PlayerRepositoryImpl;

public class RepositoryFactory {

  public static CharacterRepository getCharacterInstance() {
    return new CharacterRepositoryImpl(DataStoreFactory.getCharacterDataStore());
  }


  public static PlayerRepository getPlayerInstance() {
    return new PlayerRepositoryImpl(DataStoreFactory.getPlayerDataStore());
  }


}
