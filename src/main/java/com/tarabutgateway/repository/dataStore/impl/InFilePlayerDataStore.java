package com.tarabutgateway.repository.dataStore.impl;


import com.tarabutgateway.entity.Character;
import com.tarabutgateway.entity.Player;
import com.tarabutgateway.repository.dataStore.PresistableDataStore;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class InFilePlayerDataStore extends InMemoryPlayerDataStore implements PresistableDataStore<Player> {

  private final static String FILE_NAME = "Player";

  public InFilePlayerDataStore() {
    loadFromFile();
  }

  @Override
  public String getFileName() {
    return FILE_NAME;
  }

  @Override
  public void saveToFile() {
    try (
            FileOutputStream fos =
                    new FileOutputStream(FILE_NAME);
            ObjectOutputStream oos = new ObjectOutputStream(fos)
    ) {
      oos.writeObject(getCurrentPlayer());
    } catch (IOException ioe) {
      ioe.printStackTrace();
    }
  }

  @Override
  public void loadFromFile() {

    try (
            FileInputStream fis = new FileInputStream(FILE_NAME);
            ObjectInputStream ois = new ObjectInputStream(fis)
    ) {
      var player = (Player) ois.readObject();
      if (player == null) {
        player = loadDefault();
      }

      setCurrentPlayer(player);

    } catch (IOException | ClassNotFoundException e) {
      e.printStackTrace();
    }
  }

  public Player loadDefault() {
    var player = new Player("t", 0);
    player.setCurrentCharacter(Character.DEFAULT_CHARACTER);
    saveToFile();
    return player;
  }
}
