package com.xgame.command;

import com.xgame.GameKey;

/**
 * Command pattern to handle console readings
 */
public interface Command {


  /**
   * @return the representative keyboard character to start the command.
   */
  GameKey commandKey();


  /**
   * execute the command to start reading and processing commandline inputs
   */
  void execute();

}
