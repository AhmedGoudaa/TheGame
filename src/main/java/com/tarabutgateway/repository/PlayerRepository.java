package com.tarabutgateway.repository;

import com.tarabutgateway.entity.Player;

public interface PlayerRepository {

  Player loadPlayerData();

  Player getCurrentPlayer();

  Player updateCurrentPlayer(Player player);
}
