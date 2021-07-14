package com.tarabutgateway.repository.dataStore.impl;


import com.tarabutgateway.entity.Character;
import com.tarabutgateway.repository.dataStore.DataStore;
import com.tarabutgateway.repository.dataStore.PresistableDataStore;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class InFileCharacterDataStore extends InMemoryCharacterDataStore implements PresistableDataStore<Character> {

  private final static String FILE_NAME = "Characters";

  public InFileCharacterDataStore() {
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
      System.out.println("Saving player data to file...");
      oos.writeObject(characterMap);
      System.out.println("Saving player data Done.");
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
      Map<String, Character> map = (Map<String, Character>) ois.readObject();
      this.characterMap.putAll(map);
    } catch (IOException | ClassNotFoundException e) {
      e.printStackTrace();
    }
  }
}
