package com.tarabutgateway.factory.repo;

import com.tarabutgateway.factory.Factory;
import com.tarabutgateway.repository.dataStore.impl.InMemoryPlayerDataStore;
import com.tarabutgateway.repository.PlayerRepository;
import com.tarabutgateway.repository.impl.PlayerRepositoryImpl;

public class PlayerRepoFactory implements Factory<PlayerRepository> {

  @Override
  public PlayerRepository getInstance() {
    return new PlayerRepositoryImpl(new InMemoryPlayerDataStore());
  }
}
