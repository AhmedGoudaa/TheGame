package com.xgame.repository.dataStore.impl;


import com.xgame.entity.Character;
import com.xgame.entity.Player;
import com.xgame.repository.dataStore.PresistableDataStore;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class InFilePlayerDataStore extends InMemoryPlayerDataStore implements PresistableDataStore<Player> {

  private final static String FILE_NAME = "data/Player";

  public InFilePlayerDataStore() {
    loadFromFile();
  }

  @Override
  public String getFileName() {
    return FILE_NAME;
  }

  @Override
  public void saveToFile() {


    ClassLoader classloader = Thread.currentThread().getContextClassLoader();

    try (
            FileOutputStream fos =
                    new FileOutputStream((classloader.getResource(FILE_NAME)).getFile());
            ObjectOutputStream oos = new ObjectOutputStream(fos);
    ) {
      System.out.println("Saving player data to file...");
      oos.writeObject(getCurrentPlayer());
      System.out.println("Saving player data Done.");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void loadFromFile() {

    ClassLoader classLoader = getClass().getClassLoader();
    Player      player      = null;
    try (
            InputStream inputStream = classLoader.getResourceAsStream(FILE_NAME);
            ObjectInputStream ois = new ObjectInputStream(inputStream)
    ) {

      player = (Player) ois.readObject();

    } catch (IOException | ClassNotFoundException e) {
      System.out.println(" file is empty. load default");
    } finally {

      if (player == null) {
        player = loadDefault();
      }
      setCurrentPlayer(player);
    }
  }

  public Player loadDefault() {
    var player = new Player("t", 0);
    player.setCurrentCharacter(Character.DEFAULT_CHARACTER);
    saveToFile();
    return player;
  }
}
