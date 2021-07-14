package com.xgame.entity;


import java.io.Serializable;

public interface BaseEntity<Id> extends Serializable {
  Id getId();
}
