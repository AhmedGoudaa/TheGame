package com.xgame.repository.dataStore;

import com.xgame.entity.BaseEntity;


public interface PresistableDataStore<E extends BaseEntity> extends DataStore<E> {

  String getFileName();

  void saveToFile();

  void loadFromFile();
}
