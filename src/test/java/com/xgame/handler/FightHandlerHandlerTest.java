package com.xgame.handler;

import com.xgame.BaseTest;
import com.xgame.comman.MessageBus;
import com.xgame.comman.MessageBusFactory;
import com.xgame.entity.Character;
import com.xgame.entity.Player;
import com.xgame.message.CreateNewCharacter;
import com.xgame.message.FightAction;
import com.xgame.message.UpdateCurrentPlayerPower;
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

public class FightHandlerHandlerTest extends BaseTest {

  private MessageBus messageBus;


  @BeforeEach
  public void init() {
    messageBus = spy(MessageBusFactory.getNewDefaultInstance());
    messageBus.register(new LogMessageConsolePrinter());
  }

  @Test
  void fighting_and_update_player_score_test() {


    PlayerRepository         playerRepositoryMock     = spy(new PlayerRepositoryImpl(new InMemoryPlayerDataStore()));
    FightHandler             fightHandler             = spy(new FightHandler(playerRepositoryMock, messageBus));
    UpdatePlayerPowerHandler updatePlayerPowerHandler = spy(new UpdatePlayerPowerHandler(playerRepositoryMock, messageBus));

    messageBus.register(fightHandler);
    messageBus.register(updatePlayerPowerHandler);

    when(playerRepositoryMock.getCurrentPlayer()).thenReturn(CURRENT_PLAYER);

    Character fighter = new Character("test", 10);

    messageBus.send(new FightAction('D', fighter));

    verify(messageBus, times(1)).register(any(FightHandler.class));
    verify(messageBus, times(1)).register(any(UpdatePlayerPowerHandler.class));
    verify(messageBus, times(1)).send(any(FightAction.class));

    verify(fightHandler, times(1)).handle(any(FightAction.class));
    verify(updatePlayerPowerHandler, times(1)).handle(any(UpdateCurrentPlayerPower.class));
    verify(playerRepositoryMock, times(1)).updateCurrentPlayer(any(Player.class));
  }
}
