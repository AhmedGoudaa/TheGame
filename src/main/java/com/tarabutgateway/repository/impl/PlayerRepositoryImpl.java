package com.tarabutgateway.repository.impl;

import com.tarabutgateway.entity.Player;
import com.tarabutgateway.repository.dataStore.DataStore;
import com.tarabutgateway.repository.PlayerRepository;

public class PlayerRepositoryImpl implements PlayerRepository {

  private final DataStore<Player> playerDataStore;

  public PlayerRepositoryImpl(DataStore<Player> playerDataStore) {
    this.playerDataStore = playerDataStore;
    this.playerDataStore.load();
  }

  @Override
  public Player loadPlayerData() {
    return playerDataStore.get();
  }

  @Override
  public Player getCurrentPlayer() {
    return playerDataStore.get();
  }

  @Override
  public Player updateCurrentPlayer(Player player) {
    playerDataStore.save(player);
    return player;
  }
}
