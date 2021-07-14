package com.xgame.repository.dataStore.impl;


import com.xgame.entity.Character;
import com.xgame.repository.dataStore.PresistableDataStore;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class InFileCharacterDataStore extends InMemoryCharacterDataStore implements PresistableDataStore<Character> {

  private final static String FILE_NAME = "data/Characters";

  public InFileCharacterDataStore() {
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
            FileOutputStream fos = new FileOutputStream((classloader.getResource(FILE_NAME)).getFile());
            ObjectOutputStream oos = new ObjectOutputStream(fos);
    ) {
      System.out.println("Saving Character data to file...");
      oos.writeObject(characterMap);
      System.out.println("Saving Character data Done.");
    } catch (IOException ioe) {
      ioe.printStackTrace();
    }
  }

  @Override
  public void loadFromFile() {

    ClassLoader            classLoader = getClass().getClassLoader();
    Map<String, Character> map         = new HashMap<>();
    try (
            InputStream inputStream = classLoader.getResourceAsStream(FILE_NAME);
            ObjectInputStream ois = new ObjectInputStream(inputStream)
    ) {
      map = (Map<String, Character>) ois.readObject();
      this.characterMap.putAll(map);
    } catch (IOException | ClassNotFoundException e) {
      System.out.println(" file is empty");
    } finally {

      if (map.isEmpty()) {
        System.out.println("Loading default characters");

        map = loadDefault();
        this.characterMap.putAll(map);
        saveToFile();
      }
    }
  }

  private Map<String, Character> loadDefault() {
    return List.of(
            new Character("Batman", 100),
            new Character("Superman", 100),
            new Character("Spiderman", 100),
            new Character("X-man", 100)
    ).stream()
            .collect(Collectors.toMap(Character::getCharacterName, Function.identity(), (c1, c2) -> c2));
  }
}
