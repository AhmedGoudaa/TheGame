package com.tarabutgateway.repository.dataStore.impl;


import com.tarabutgateway.entity.Player;
import com.tarabutgateway.repository.dataStore.DataStore;

import java.util.Set;

public class InMemoryPlayerDataStore implements DataStore<Player> {

  private   Player currentPlayer;

  @Override
  public void save(Player player) {
    this.currentPlayer = player;
  }

  @Override
  public Player get() {
    return currentPlayer;
  }

  @Override
  public Set<Player> getAll() {
    return Set.of(currentPlayer);
  }

  @Override
  public void delete(Player player) {
    // do Nothing
  }

  public Player getCurrentPlayer() {
    return currentPlayer;
  }

  public void setCurrentPlayer(Player currentPlayer) {
    this.currentPlayer = currentPlayer;
  }
}
