package com.tarabutgateway.command;

import com.tarabutgateway.comman.Message;
import com.tarabutgateway.entity.Character;
import com.tarabutgateway.entity.FightData;

public class FightAction implements Message<FightData> {


  private final FightData fightData;

  public FightAction(char keyboardKey, Character fightAgainst) {
    this.fightData = new FightData(keyboardKey, fightAgainst );
  }


  @Override
  public FightData getData() {
    return fightData;
  }


}
