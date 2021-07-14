package com.xgame.repository;

import com.xgame.entity.Player;

public interface PlayerRepository extends Repository {

  Player loadPlayerData();

  Player getCurrentPlayer();

  Player updateCurrentPlayer(Player player);
}
