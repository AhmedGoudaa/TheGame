package com.tarabutgateway.common.service;

import com.tarabutgateway.entity.Character;
import com.tarabutgateway.entity.Player;
import com.tarabutgateway.repository.PlayerRepository;
import com.tarabutgateway.service.impl.PlayerServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PlayerServiceTest {

  @Mock
  PlayerRepository playerRepository;

  @InjectMocks
  PlayerServiceImpl playerService;


  @Test
  public void update_Player_Character(){

    Player player = new Player("Test", 100);

    when(playerRepository.getCurrentPlayer()).thenReturn(player);
    when(playerRepository.updateCurrentPlayer(player)).thenReturn(player);

    Character character = new Character("x-man", 1000);

    Player updatePlayer = playerService.updateCharacter(character);

    verify(playerRepository, times(1)).updateCurrentPlayer(any());

    assertThat(updatePlayer.getCurrentCharacter() , equalTo(character));

  }


}
