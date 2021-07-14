package com.tarabutgateway.repository.dataStore;

import com.tarabutgateway.entity.BaseEntity;

import java.util.Set;


public interface PresistableDataStore<E extends BaseEntity> extends DataStore<E> {

   String getFileName();

   void saveToFile();

   void loadFromFile();
}
