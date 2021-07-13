package com.tarabutgateway.repository.dataStore;

import com.tarabutgateway.entity.BaseEntity;

import java.util.Set;

public interface DataStore<E extends BaseEntity> {

  void save(E e);

  void delete(E e);

  void load();

  E get();

  Set<E> getAll();
}
