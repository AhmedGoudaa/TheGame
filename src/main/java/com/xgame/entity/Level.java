package com.xgame.entity;

public enum Level {

  /**
   * Level from 1 to 3
   */
  LEVEL_1(0, 10, 2),
  LEVEL_2(11, 20, 4),
  LEVEL_3(21, 30, 6),
  MAX_LEVEL(31, 50, 10);


  int scoreFrom;
  int scoreTo;
  int levelWeight;

  Level(int scoreFrom, int scoreTo, int levelWeight) {
    this.scoreFrom   = scoreFrom;
    this.scoreTo     = scoreTo;
    this.levelWeight = levelWeight;
  }

  public static Level getLevel(int score) {
    if (score >= 0 && score <= 10) {
      return LEVEL_1;
    } else if (score >= 11 && score <= 20) {
      return LEVEL_2;
    } else if (score >= 21 && score <= 30) {
      return LEVEL_3;
    } else {
      return MAX_LEVEL;
    }
  }

  public static int getLevelNumber(int score) {
    if (score >= 0 && score <= 10) {
      return 1;
    } else if (score >= 11 && score <= 20) {
      return 2;
    } else if (score >= 21 && score <= 30) {
      return 3;
    } else {
      return 4;
    }
  }

  public int getScoreFrom() {
    return scoreFrom;
  }

  public int getScoreTo() {
    return scoreTo;
  }

  public int getLevelWeight() {
    return levelWeight;
  }
}
