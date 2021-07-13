package com.tarabutgateway.service;

import com.tarabutgateway.entity.Character;
import com.tarabutgateway.entity.Player;

public interface PlayerService {

  Player getPlayer();

  Player updateCharacter(Character newCharacter);


}
