package com.tarabutgateway.common.service;

import com.tarabutgateway.entity.Character;
import com.tarabutgateway.exceptions.CharacterAlreadyExists;
import com.tarabutgateway.repository.CharacterRepository;
import com.tarabutgateway.service.impl.CharacterServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CharacterServiceTest {

  @Mock
  CharacterRepository characterRepository;

  @InjectMocks
  CharacterServiceImpl characterService;


  @Test
  public void add_new_Character() throws CharacterAlreadyExists {


    Set<Character> characterList = getCharacterList();

    var characterName= "test";
    var power = 0;

    Character character = new Character(characterName, power);

    when(characterRepository.getAll()).thenReturn(characterList);
    when(characterRepository.save(any())).thenReturn(character);

    Character newCharacter = characterService.createNewCharacter(characterName, power);

    verify(characterRepository, times(1)).save(any());
    assertThat(newCharacter.getCharacterName(), equalTo(characterName));

  }  @Test
  public void add_new_Character_that_already_exists() throws CharacterAlreadyExists {
    Set<Character> characterList = getCharacterList();
    Character character = characterList.stream().findFirst().get();

    when(characterRepository.getAll()).thenReturn(characterList);
    assertThrows(CharacterAlreadyExists.class, () -> characterService.createNewCharacter(character.getCharacterName(), character.getPower()));

  }

  private Set<Character> getCharacterList() {
    return Set.of(
            new Character("1", 100),
            new Character("2", 100),
            new Character("3", 100),
            new Character("4", 100)
    );
  }


}
