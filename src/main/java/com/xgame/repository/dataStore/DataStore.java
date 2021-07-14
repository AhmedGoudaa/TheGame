package com.xgame.repository.dataStore;

import com.xgame.entity.BaseEntity;

import java.util.Set;

public interface DataStore<E extends BaseEntity> {

  void save(E e);

  void delete(E e);


  E get();

  Set<E> getAll();
}
