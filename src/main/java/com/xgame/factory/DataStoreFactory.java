package com.xgame.factory;

import com.xgame.entity.Character;
import com.xgame.entity.Player;
import com.xgame.repository.dataStore.PresistableDataStore;
import com.xgame.repository.dataStore.impl.InFileCharacterDataStore;
import com.xgame.repository.dataStore.impl.InFilePlayerDataStore;

public class DataStoreFactory {

  public static final PresistableDataStore<Player> getPlayerDataStore() {
    return PresistableDataStores.PLAYER.instance;
  }

  public static final PresistableDataStore<Character> getCharacterDataStore() {
    return PresistableDataStores.CHAR.instance;
  }

  public enum PresistableDataStores {
    PLAYER(new InFilePlayerDataStore()),
    CHAR(new InFileCharacterDataStore());

    private final PresistableDataStore instance;

    PresistableDataStores(PresistableDataStore presistableDataStore) {
      this.instance = presistableDataStore;
    }

    public PresistableDataStore getInstance() {
      return instance;
    }
  }
}
