package com.xgame.message;

import com.xgame.comman.Message;
import com.xgame.entity.Character;
import com.xgame.entity.FightData;

public class FightAction implements Message<FightData> {


  private final FightData fightData;

  public FightAction(char keyboardKey, Character fightAgainst) {
    this.fightData = new FightData(keyboardKey, fightAgainst);
  }


  @Override
  public FightData getData() {
    return fightData;
  }


}
