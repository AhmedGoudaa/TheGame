package com.xgame;

import com.xgame.entity.Character;
import com.xgame.entity.Player;

public class BaseTest {
  public static final Character TEST_CHARACTER        = new Character("TEST_CHARACTER",100);
  public static final Player    CURRENT_PLAYER = new Player("TEST",1, TEST_CHARACTER);
}
