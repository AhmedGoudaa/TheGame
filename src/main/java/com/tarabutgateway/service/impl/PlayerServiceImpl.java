package com.tarabutgateway.service.impl;

import com.tarabutgateway.entity.Character;
import com.tarabutgateway.entity.Player;
import com.tarabutgateway.repository.PlayerRepository;
import com.tarabutgateway.service.PlayerService;

public class PlayerServiceImpl implements PlayerService {

  private final PlayerRepository playerRepository;

  public PlayerServiceImpl(PlayerRepository playerRepository) {
    this.playerRepository = playerRepository;
  }

  @Override
  public Player getPlayer() {
    return playerRepository.getCurrentPlayer();
  }

  @Override
  public Player updateCharacter(Character newCharacter) {
    Player player = getPlayer();
    player.setCurrentCharacter(newCharacter);
    return playerRepository.updateCurrentPlayer(player);
  }
}
