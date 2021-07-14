package com.xgame.handler;

import com.xgame.BaseTest;
import com.xgame.comman.MessageBus;
import com.xgame.comman.MessageBusFactory;
import com.xgame.entity.Player;
import com.xgame.message.ListAllCharacters;
import com.xgame.message.LogMessage;
import com.xgame.message.UpdateCurrentPlayerPower;
import com.xgame.repository.dataStore.impl.InMemoryCharacterDataStore;
import com.xgame.repository.dataStore.impl.InMemoryPlayerDataStore;
import com.xgame.repository.impl.CharacterRepositoryImpl;
import com.xgame.repository.impl.PlayerRepositoryImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class UpdatePlayerPowerHandlerTest extends BaseTest {

  private MessageBus messageBus;


  @BeforeEach
  public void init() {
    messageBus = spy(MessageBusFactory.getNewDefaultInstance());
    messageBus.register(new LogMessageConsolePrinter());

  }

  @Test
  void create_new_character_test() {

    PlayerRepositoryImpl  playerRepository  = spy(new PlayerRepositoryImpl(new InMemoryPlayerDataStore()));
    UpdatePlayerPowerHandler  updatePlayerPowerHandler  = spy(new UpdatePlayerPowerHandler(playerRepository, messageBus));

    messageBus.register(updatePlayerPowerHandler);

    when(playerRepository.getCurrentPlayer()).thenReturn(CURRENT_PLAYER);

    messageBus.send(new UpdateCurrentPlayerPower(10));

    verify(messageBus, times(1)).register(any(UpdatePlayerPowerHandler.class));

    verify(playerRepository, times(1)).updateCurrentPlayer(any(Player.class));
  }
}
