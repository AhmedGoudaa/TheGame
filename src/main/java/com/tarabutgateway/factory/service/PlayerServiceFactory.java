package com.tarabutgateway.factory.service;

import com.tarabutgateway.factory.Factory;
import com.tarabutgateway.factory.repo.PlayerRepoFactory;
import com.tarabutgateway.service.PlayerService;
import com.tarabutgateway.service.impl.PlayerServiceImpl;

public class PlayerServiceFactory implements Factory<PlayerService> {

  private final PlayerRepoFactory playerRepoFactory;

  public PlayerServiceFactory() {
    this.playerRepoFactory = new PlayerRepoFactory();
  }


  @Override
  public PlayerService getInstance() {
    return new PlayerServiceImpl(playerRepoFactory.getInstance());
  }
}
