package com.xgame.handler;

import com.xgame.BaseTest;
import com.xgame.comman.MessageBus;
import com.xgame.comman.MessageBusFactory;
import com.xgame.entity.Character;
import com.xgame.message.CreateNewCharacter;
import com.xgame.message.ListAllCharacters;
import com.xgame.message.LogMessage;
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

public class ListAllCharacterHandlerTest extends BaseTest {

  private MessageBus messageBus;


  @BeforeEach
  public void init() {
    messageBus = spy(MessageBusFactory.getNewDefaultInstance());

  }

  @Test
  void create_new_character_test() {


    CharacterRepositoryImpl  characterRepositoryMock  = spy(new CharacterRepositoryImpl(new InMemoryCharacterDataStore()));
    ListAllCharacterHandler  listAllCharacterHandler  = spy(new ListAllCharacterHandler(characterRepositoryMock, messageBus));
    LogMessageConsolePrinter logMessageConsolePrinter = spy(new LogMessageConsolePrinter());

    messageBus.register(logMessageConsolePrinter);
    messageBus.register(listAllCharacterHandler);

    when(characterRepositoryMock.getAll()).thenReturn(Collections.singleton(TEST_CHARACTER));

    messageBus.send(new ListAllCharacters());

    verify(messageBus, times(1)).register(any(ListAllCharacterHandler.class));
    verify(messageBus, times(1)).register(any(LogMessageConsolePrinter.class));

    verify(messageBus, times(1)).send(any(ListAllCharacters.class));

    verify(characterRepositoryMock, times(1)).getAll();

    verify(messageBus, times(1)).send(any(LogMessage.class));

  }
}
