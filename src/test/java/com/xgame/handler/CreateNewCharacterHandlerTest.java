package com.xgame.handler;

import com.xgame.BaseTest;
import com.xgame.comman.MessageBus;
import com.xgame.comman.MessageBusFactory;
import com.xgame.entity.Character;
import com.xgame.entity.Player;
import com.xgame.message.ChoseCharacter;
import com.xgame.message.CreateNewCharacter;
import com.xgame.repository.dataStore.impl.InMemoryCharacterDataStore;
import com.xgame.repository.impl.CharacterRepositoryImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class CreateNewCharacterHandlerTest extends BaseTest {

  private MessageBus messageBus;


  @BeforeEach
  public void init() {
    messageBus = spy(MessageBusFactory.getNewDefaultInstance());
    messageBus.register(new LogMessageConsolePrinter());
  }

  @Test
  void create_new_character_test() {


    CharacterRepositoryImpl   characterRepositoryMock = spy(new CharacterRepositoryImpl(new InMemoryCharacterDataStore()));
    CreateNewCharacterHandler createNewCharacterHandler   = spy(new CreateNewCharacterHandler(characterRepositoryMock, messageBus));

    messageBus.register(createNewCharacterHandler);

    when(characterRepositoryMock.getAll()).thenReturn(Collections.singleton(TEST_CHARACTER));

    messageBus.send(new CreateNewCharacter(new Character("test",1002)));
//
//
    verify(messageBus, times(1)).register(any(CreateNewCharacterHandler.class));
    verify(messageBus, times(1)).send(any(CreateNewCharacter.class));
    verify(createNewCharacterHandler, times(1)).handle(any(CreateNewCharacter.class));
    verify(characterRepositoryMock, times(1)).save(any(Character.class));
  }
}
