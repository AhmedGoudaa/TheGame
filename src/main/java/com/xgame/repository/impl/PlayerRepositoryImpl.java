package com.xgame.repository.impl;

import com.xgame.entity.Player;
import com.xgame.repository.PlayerRepository;
import com.xgame.repository.dataStore.DataStore;

public class PlayerRepositoryImpl implements PlayerRepository {

  private final DataStore<Player> playerDataStore;

  public PlayerRepositoryImpl(DataStore<Player> playerDataStore) {
    this.playerDataStore = playerDataStore;
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
