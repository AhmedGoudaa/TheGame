package com.tarabutgateway.factory.repo;

import com.tarabutgateway.entity.Character;
import com.tarabutgateway.entity.Player;
import com.tarabutgateway.repository.dataStore.PresistableDataStore;
import com.tarabutgateway.repository.dataStore.impl.InFileCharacterDataStore;
import com.tarabutgateway.repository.dataStore.impl.InFilePlayerDataStore;

public class DataStoreFactory {

  public static final PresistableDataStore<Player> getPlayerDataStore() {
    return PresistableDataStores.PLAYER.instance;
  }

  public static final PresistableDataStore<Character> getCharacterDataStore(){
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
