package com.tarabutgateway.repository;

import com.tarabutgateway.entity.Player;

public interface PlayerRepository extends Repository {

  Player loadPlayerData();

  Player getCurrentPlayer();

  Player updateCurrentPlayer(Player player);
}
