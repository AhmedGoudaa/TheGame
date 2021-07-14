package com.xgame.handler;

import com.xgame.BaseTest;
import com.xgame.comman.MessageBus;
import com.xgame.comman.MessageBusFactory;
import com.xgame.entity.Player;
import com.xgame.message.ChoseCharacter;
import com.xgame.repository.PlayerRepository;
import com.xgame.repository.dataStore.impl.InMemoryPlayerDataStore;
import com.xgame.repository.impl.PlayerRepositoryImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ChoseCharacterHandlerTest extends BaseTest {

  private MessageBus messageBus;


  @BeforeEach
  public void init() {
    messageBus = spy(MessageBusFactory.getNewDefaultInstance());
    messageBus.register(new LogMessageConsolePrinter());
  }

  @Test
  void chose_character_for_current_player_test() {


    PlayerRepository      playerRepositoryMock  = spy(new PlayerRepositoryImpl(new InMemoryPlayerDataStore()));
    ChoseCharacterHandler choseCharacterHandler = spy(new ChoseCharacterHandler(playerRepositoryMock, messageBus));

    messageBus.register(choseCharacterHandler);
    when(playerRepositoryMock.getCurrentPlayer()).thenReturn(CURRENT_PLAYER);

    messageBus.send(new ChoseCharacter(TEST_CHARACTER));


    verify(messageBus, times(1)).register(any(ChoseCharacterHandler.class));
    verify(messageBus, times(1)).send(any(ChoseCharacter.class));
    verify(choseCharacterHandler, times(1)).handle(any(ChoseCharacter.class));
    verify(playerRepositoryMock, times(1)).updateCurrentPlayer(any(Player.class));
  }
}
