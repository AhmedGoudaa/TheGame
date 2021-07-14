package com.xgame.service;

import com.xgame.entity.Character;
import com.xgame.entity.Player;

public interface PlayerService {

  Player getPlayer();

  Player updateCharacter(Character newCharacter);


}
